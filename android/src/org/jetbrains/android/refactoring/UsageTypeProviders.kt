/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.android.refactoring

import com.android.tools.idea.gradle.project.sync.GradleFiles
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.xml.XmlFile
import com.intellij.usages.impl.rules.UsageType
import com.intellij.usages.impl.rules.UsageTypeProvider
import com.intellij.util.xml.DomManager
import org.jetbrains.android.dom.AndroidDomElement
import org.jetbrains.android.dom.manifest.ManifestDomFileDescription
import org.jetbrains.android.facet.AndroidFacet
import org.jetbrains.android.util.AndroidResourceUtil
import org.jetbrains.plugins.groovy.GroovyLanguage

/**
 * Recognizes Groovy elements in files that [GradleFiles] considers to be build scripts.
 */
class GradleUsageTypeProvider : UsageTypeProvider {
  override fun getUsageType(element: PsiElement?): UsageType? {
    if (element?.language != GroovyLanguage) return null
    return if (GradleFiles.getInstance(element.project).isGradleFile(element.containingFile)) GRADLE_USAGE_TYPE else null
  }

  companion object {
    private val GRADLE_USAGE_TYPE = UsageType("{0} in Gradle build script")
  }
}

/**
 * Recognizes Android XML files and provides a better description than [com.intellij.util.xml.DomUsageTypeProvider].
 */
class AndroidDomUsageTypeProvider : UsageTypeProvider {
  override fun getUsageType(element: PsiElement): UsageType? {
    val xmlFile = element.containingFile as? XmlFile ?: return null
    val domManager = DomManager.getDomManager(xmlFile.project) ?: return null
    return when (domManager.getFileElement(xmlFile, AndroidDomElement::class.java)?.fileDescription) {
      null -> null
      is ManifestDomFileDescription -> ANDROID_MANIFEST_USAGE_TYPE
      else -> ANDROID_RESOURCES_XML_USAGE_TYPE
    }
  }

  companion object {
    private val ANDROID_RESOURCES_XML_USAGE_TYPE = UsageType("{0} in Android resources XML")
    private val ANDROID_MANIFEST_USAGE_TYPE = UsageType("{0} in Android manifest")
  }
}

/**
 * Recognizes references to `R` and `Manifest` classes in code and provides a more specific description than the default [UsageType.READ].
 *
 * @see com.intellij.usages.impl.rules.UsageTypeGroupingRule.getParentGroupFor
 */
class AndroidResourceReferenceInCodeUsageTypeProvider : UsageTypeProvider {
  override fun getUsageType(element: PsiElement): UsageType? {
    val referenceExpression = element as? PsiReferenceExpression ?: return null
    val facet = AndroidFacet.getInstance(element) ?: return null
    val fieldInfo = AndroidResourceUtil.getReferredResourceOrManifestField(facet, referenceExpression, false)
    return when {
      fieldInfo == null -> null
      fieldInfo.isFromManifest -> PERMISSION_REFERENCE_IN_CODE
      else -> RESOURCE_REFERENCE_IN_CODE
    }
  }

  companion object {
    private val RESOURCE_REFERENCE_IN_CODE = UsageType("Resource reference in code")
    private val PERMISSION_REFERENCE_IN_CODE = UsageType("Permission reference in code")
  }
}
