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
package com.android.tools.idea.gradle.project.sync.ng.nosyncbuilder.legacyfacade

import com.android.builder.model.level2.DependencyGraphs
import com.android.tools.idea.gradle.project.sync.ng.nosyncbuilder.interfaces.variant.JavaArtifact
import com.android.tools.idea.gradle.project.sync.ng.nosyncbuilder.legacyfacade.library.LegacyDependencyGraphsStub
import com.android.tools.idea.gradle.project.sync.ng.nosyncbuilder.legacyfacade.stubs.DependenciesStub
import com.android.tools.idea.gradle.project.sync.ng.nosyncbuilder.misc.OldDependencies
import com.android.tools.idea.gradle.project.sync.ng.nosyncbuilder.misc.OldJavaArtifact
import java.io.File

open class LegacyJavaArtifact(private val javaArtifact: JavaArtifact) : OldJavaArtifact, LegacyBaseArtifact(javaArtifact) {
  override fun getMockablePlatformJar(): File? = javaArtifact.mockablePlatformJar

  override fun toString(): String = "LegacyJavaArtifact{mockablePlatformJar=$mockablePlatformJar}"
}

class LegacyJavaArtifactStub(private val javaArtifact: JavaArtifact) : LegacyJavaArtifact(javaArtifact) {
  override fun getDependencyGraphs(): DependencyGraphs = LegacyDependencyGraphsStub(javaArtifact.dependencies)

  override fun getDependencies(): OldDependencies = DependenciesStub()
  @Deprecated("use dependencies instead", ReplaceWith("getDependencies()"))
  override fun getCompileDependencies(): OldDependencies = dependencies
}
