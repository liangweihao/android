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
package com.android.tools.idea.gradle.structure.model.android

import com.android.tools.idea.gradle.structure.model.PsProjectImpl
import com.android.tools.idea.gradle.structure.model.meta.*
import com.android.tools.idea.testing.AndroidGradleTestCase
import com.android.tools.idea.testing.TestProjectPaths
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Ignore
import java.io.File

class PsBuildTypeTest : AndroidGradleTestCase() {

  fun testDescriptor() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    val project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    val appModule = project.findModuleByName("app") as PsAndroidModule
    assertThat(appModule, notNullValue())

    val buildType = appModule.findBuildType("release")
    assertThat(buildType, notNullValue()); buildType!!

    assertThat(buildType.descriptor.testEnumerateProperties(), equalTo(PsBuildType.BuildTypeDescriptors.testEnumerateProperties()))
  }

  fun testProperties() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    val project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    run {
      val appModule = project.findModuleByName("app") as PsAndroidModule
      assertThat(appModule, notNullValue())

      val buildType = appModule.findBuildType("release")
      assertThat(buildType, notNullValue()); buildType!!

      val applicationIdSuffix = PsBuildType.BuildTypeDescriptors.applicationIdSuffix.bind(buildType).getValue()
      val debuggable = PsBuildType.BuildTypeDescriptors.debuggable.bind(buildType).getValue()
      // TODO(b/70501607): Decide on val embedMicroApp = PsBuildType.BuildTypeDescriptors.embedMicroApp.getValue(buildType)
      val jniDebuggable = PsBuildType.BuildTypeDescriptors.jniDebuggable.bind(buildType).getValue()
      val minifyEnabled = PsBuildType.BuildTypeDescriptors.minifyEnabled.bind(buildType).getValue()
      val multiDexEnabled = PsBuildType.BuildTypeDescriptors.multiDexEnabled.bind(buildType).getValue()
      // TODO(b/70501607): Decide on val pseudoLocalesEnabled = PsBuildType.BuildTypeDescriptors.pseudoLocalesEnabled.getValue(buildType)
      val renderscriptDebuggable = PsBuildType.BuildTypeDescriptors.renderscriptDebuggable.bind(buildType).getValue()
      val renderscriptOptimLevel = PsBuildType.BuildTypeDescriptors.renderscriptOptimLevel.bind(buildType).getValue()
      val signingConfig = PsBuildType.BuildTypeDescriptors.signingConfig.bind(buildType).getValue()
      // TODO(b/70501607): Decide on val testCoverageEnabled = PsBuildType.BuildTypeDescriptors.testCoverageEnabled.getValue(buildType)
      val versionNameSuffix = PsBuildType.BuildTypeDescriptors.versionNameSuffix.bind(buildType).getValue()
      val zipAlignEnabled = PsBuildType.BuildTypeDescriptors.zipAlignEnabled.bind(buildType).getValue()
      val matchingFallbacks = PsBuildType.BuildTypeDescriptors.matchingFallbacks.bind(buildType).getEditableValues().map { it.getValue() }
      val proGuardFiles = PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).getEditableValues().map { it.getValue() }
      val manifestPlaceholders = PsBuildType.BuildTypeDescriptors.manifestPlaceholders.bind(buildType).getValue()

      assertThat(applicationIdSuffix.resolved.asTestValue(), equalTo("suffix"))
      assertThat(applicationIdSuffix.parsedValue.asTestValue(), equalTo("suffix"))

      assertThat(debuggable.resolved.asTestValue(), equalTo(false))
      assertThat(debuggable.parsedValue.asTestValue(), equalTo(false))

      assertThat(jniDebuggable.resolved.asTestValue(), equalTo(false))
      assertThat(jniDebuggable.parsedValue.asTestValue(), equalTo(false))

      assertThat(minifyEnabled.resolved.asTestValue(), equalTo(false))
      assertThat(minifyEnabled.parsedValue.asTestValue(), equalTo(false))

      assertThat(multiDexEnabled.resolved.asTestValue(), nullValue())
      assertThat(multiDexEnabled.parsedValue.asTestValue(), nullValue())

      assertThat(renderscriptDebuggable.resolved.asTestValue(), equalTo(false))
      assertThat(renderscriptDebuggable.parsedValue.asTestValue(), nullValue())

      assertThat(renderscriptOptimLevel.resolved.asTestValue(), equalTo(2))
      assertThat(renderscriptOptimLevel.parsedValue.asTestValue(), equalTo(2))

      assertThat(signingConfig.resolved.asTestValue(), nullValue())
      assertThat(
        signingConfig.parsedValue,
        equalTo<Annotated<ParsedValue<Unit>>>(ParsedValue.Set.Parsed(Unit, DslText.Reference("signingConfigs.myConfig")).annotated()))

      assertThat(versionNameSuffix.resolved.asTestValue(), equalTo("vsuffix"))
      assertThat(versionNameSuffix.parsedValue.asTestValue(), equalTo("vsuffix"))

      assertThat(zipAlignEnabled.resolved.asTestValue(), equalTo(true))
      assertThat(zipAlignEnabled.parsedValue.asTestValue(), nullValue())

      assertThat(matchingFallbacks.size, equalTo(0))

      assertThat(proGuardFiles.size, equalTo(3))
      assertThat(proGuardFiles[0].resolved.asTestValue(), nullValue())
      // TODO(b/72052622): assertThat(proGuardFiles[0].parsedValue, instanceOf(ParsedValue.Set.Parsed::class.java))
      // TODO(b/72052622): assertThat(
      //  (proGuardFiles[0].parsedValue as ParsedValue.Set.Parsed<File>).dslText?.mode,
      //  equalTo(DslMode.OTHER_UNPARSED_DSL_TEXT)
      //)
      // TODO(b/72052622): assertThat(
      //  (proGuardFiles[0].parsedValue as ParsedValue.Set.Parsed<File>).dslText?.text,
      //  equalTo("getDefaultProguardFile('proguard-android.txt')")
      //)

      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[1].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[1].parsedValue.asTestValue(), equalTo(File("proguard-rules.txt")))

      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[2].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[2].parsedValue.asTestValue(), equalTo(File("proguard-rules2.txt")))

      assertThat(manifestPlaceholders.resolved.asTestValue(), equalTo(mapOf()))
      assertThat(manifestPlaceholders.parsedValue.asTestValue(), nullValue())
    }
    run {
      val appModule = project.findModuleByName("app") as PsAndroidModule
      assertThat(appModule, notNullValue())

      val buildType = appModule.findBuildType("specialRelease")
      assertThat(buildType, notNullValue()); buildType!!
      val matchingFallbacks = PsBuildType.BuildTypeDescriptors.matchingFallbacks.bind(buildType).getEditableValues().map { it.getValue() }

      assertThat(matchingFallbacks.size, equalTo(2))
      assertThat(matchingFallbacks[0].resolved.asTestValue(), nullValue())
      assertThat(matchingFallbacks[0].parsedValue.asTestValue(), equalTo("release"))
      assertThat(matchingFallbacks[1].resolved.asTestValue(), nullValue())
      assertThat(matchingFallbacks[1].parsedValue.asTestValue(), equalTo("debug"))
    }
    run {
      val appModule = project.findModuleByName("lib") as PsAndroidModule
      assertThat(appModule, notNullValue())

      val buildType = appModule.findBuildType("release")
      assertThat(buildType, notNullValue()); buildType!!

      val consumerProGuardFiles = PsBuildType.BuildTypeDescriptors.consumerProGuardFiles.bind(buildType).getValue()
      assertThat(consumerProGuardFiles.parsedValue.asTestValue(), equalTo(listOf(File("other.pro"))))
      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(consumerProGuardFiles.resolved.asTestValue(), equalTo(listOf()))
    }
  }

  fun testProperties_defaultResolved() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    val project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    val appModule = project.findModuleByName("app") as PsAndroidModule
    assertThat(appModule, notNullValue())

    val buildType = appModule.findBuildType("debug")
    assertThat(buildType, notNullValue()); buildType!!
    assertFalse(buildType.isDeclared)

    val applicationIdSuffix = PsBuildType.BuildTypeDescriptors.applicationIdSuffix.bind(buildType).getValue()
    val debuggable = PsBuildType.BuildTypeDescriptors.debuggable.bind(buildType).getValue()
    // TODO(b/70501607): Decide on val embedMicroApp = PsBuildType.BuildTypeDescriptors.embedMicroApp.getValue(buildType)
    val jniDebuggable = PsBuildType.BuildTypeDescriptors.jniDebuggable.bind(buildType).getValue()
    val minifyEnabled = PsBuildType.BuildTypeDescriptors.minifyEnabled.bind(buildType).getValue()
    val multiDexEnabled = PsBuildType.BuildTypeDescriptors.multiDexEnabled.bind(buildType).getValue()
    // TODO(b/70501607): Decide on val pseudoLocalesEnabled = PsBuildType.BuildTypeDescriptors.pseudoLocalesEnabled.getValue(buildType)
    val renderscriptDebuggable = PsBuildType.BuildTypeDescriptors.renderscriptDebuggable.bind(buildType).getValue()
    val renderscriptOptimLevel = PsBuildType.BuildTypeDescriptors.renderscriptOptimLevel.bind(buildType).getValue()
    // TODO(b/70501607): Decide on val testCoverageEnabled = PsBuildType.BuildTypeDescriptors.testCoverageEnabled.getValue(buildType)
    val versionNameSuffix = PsBuildType.BuildTypeDescriptors.versionNameSuffix.bind(buildType).getValue()
    val zipAlignEnabled = PsBuildType.BuildTypeDescriptors.zipAlignEnabled.bind(buildType).getValue()
    val manifestPlaceholders = PsBuildType.BuildTypeDescriptors.manifestPlaceholders.bind(buildType).getValue()

    assertThat(applicationIdSuffix.resolved.asTestValue(), nullValue())
    assertThat(applicationIdSuffix.parsedValue.asTestValue(), nullValue())

    assertThat(debuggable.resolved.asTestValue(), equalTo(true))
    assertThat(debuggable.parsedValue.asTestValue(), nullValue())

    assertThat(jniDebuggable.resolved.asTestValue(), equalTo(false))
    assertThat(jniDebuggable.parsedValue.asTestValue(), nullValue())

    assertThat(minifyEnabled.resolved.asTestValue(), equalTo(false))
    assertThat(minifyEnabled.parsedValue.asTestValue(), nullValue())

    assertThat(multiDexEnabled.resolved.asTestValue(), nullValue())
    assertThat(multiDexEnabled.parsedValue.asTestValue(), nullValue())

    assertThat(renderscriptDebuggable.resolved.asTestValue(), equalTo(false))
    assertThat(renderscriptDebuggable.parsedValue.asTestValue(), nullValue())

    assertThat(renderscriptOptimLevel.resolved.asTestValue(), equalTo(3))
    assertThat(renderscriptOptimLevel.parsedValue.asTestValue(), nullValue())

    // TODO(b/79142681) signingConfig resolved value is always null.

    assertThat(versionNameSuffix.resolved.asTestValue(), nullValue())
    assertThat(versionNameSuffix.parsedValue.asTestValue(), nullValue())

    assertThat(zipAlignEnabled.resolved.asTestValue(), equalTo(true))
    assertThat(zipAlignEnabled.parsedValue.asTestValue(), nullValue())

    assertThat(manifestPlaceholders.resolved.asTestValue(), equalTo(mapOf()))
    assertThat(manifestPlaceholders.parsedValue.asTestValue(), nullValue())
  }

  fun testSetProperties() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    var project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    var appModule = project.findModuleByName("app") as PsAndroidModule
    assertThat(appModule, notNullValue())

    val buildType = appModule.findBuildType("release")
    assertThat(buildType, notNullValue()); buildType!!

    buildType.applicationIdSuffix = "new_suffix".asParsed()
    buildType.debuggable = true.asParsed()
    buildType.jniDebuggable = true.asParsed()
    buildType.minifyEnabled = true.asParsed()
    buildType.multiDexEnabled = true.asParsed()
    buildType.multiDexEnabled = false.asParsed()
    buildType.renderscriptDebuggable = true.asParsed()
    buildType.renderscriptOptimLevel = 3.asParsed()
    buildType.versionNameSuffix = "new_vsuffix".asParsed()
    buildType.zipAlignEnabled = false.asParsed()
    PsBuildType.BuildTypeDescriptors.signingConfig.bind(buildType).setParsedValue(ParsedValue.NotSet)
    PsBuildType.BuildTypeDescriptors.matchingFallbacks.bind(buildType).run {
      addItem(0).setParsedValue("debug".asParsed())
    }
    PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).run {
      deleteItem(1)
      val editableProGuardFiles = getEditableValues()
      editableProGuardFiles[1].setParsedValue(File("a.txt").asParsed())
      addItem(2).setParsedValue(File("z.txt").asParsed())
    }

    PsBuildType.BuildTypeDescriptors.manifestPlaceholders.bind(buildType).run {
      addEntry("b").setParsedValue("v".asParsed())
      changeEntryKey("b", "v")
      deleteEntry("v")
    }


    fun verifyValues(buildType: PsBuildType, afterSync: Boolean = false) {
      val applicationIdSuffix = PsBuildType.BuildTypeDescriptors.applicationIdSuffix.bind(buildType).getValue()
      val debuggable = PsBuildType.BuildTypeDescriptors.debuggable.bind(buildType).getValue()
      // TODO(b/70501607): Decide on val embedMicroApp = PsBuildType.BuildTypeDescriptors.embedMicroApp.getValue(buildType)
      val jniDebuggable = PsBuildType.BuildTypeDescriptors.jniDebuggable.bind(buildType).getValue()
      val minifyEnabled = PsBuildType.BuildTypeDescriptors.minifyEnabled.bind(buildType).getValue()
      val multiDexEnabled = PsBuildType.BuildTypeDescriptors.multiDexEnabled.bind(buildType).getValue()
      // TODO(b/70501607): Decide on val pseudoLocalesEnabled = PsBuildType.BuildTypeDescriptors.pseudoLocalesEnabled.getValue(buildType)
      val renderscriptDebuggable = PsBuildType.BuildTypeDescriptors.renderscriptDebuggable.bind(buildType).getValue()
      val renderscriptOptimLevel = PsBuildType.BuildTypeDescriptors.renderscriptOptimLevel.bind(buildType).getValue()
      val signingConfig = PsBuildType.BuildTypeDescriptors.signingConfig.bind(buildType).getValue()
      // TODO(b/70501607): Decide on val testCoverageEnabled = PsBuildType.BuildTypeDescriptors.testCoverageEnabled.getValue(buildType)
      val versionNameSuffix = PsBuildType.BuildTypeDescriptors.versionNameSuffix.bind(buildType).getValue()
      val zipAlignEnabled = PsBuildType.BuildTypeDescriptors.zipAlignEnabled.bind(buildType).getValue()
      val matchingFallbacks = PsBuildType.BuildTypeDescriptors.matchingFallbacks.bind(buildType).getEditableValues().map { it.getValue() }
      val proGuardFiles = PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).getEditableValues().map { it.getValue() }
      val manifestPlaceholders = PsBuildType.BuildTypeDescriptors.manifestPlaceholders.bind(buildType).getValue()

      assertThat(applicationIdSuffix.parsedValue.asTestValue(), equalTo("new_suffix"))
      assertThat(debuggable.parsedValue.asTestValue(), equalTo(true))
      assertThat(jniDebuggable.parsedValue.asTestValue(), equalTo(true))
      assertThat(minifyEnabled.parsedValue.asTestValue(), equalTo(true))
      assertThat(multiDexEnabled.parsedValue.asTestValue(), equalTo(false))
      assertThat(renderscriptDebuggable.parsedValue.asTestValue(), equalTo(true))
      assertThat(renderscriptOptimLevel.parsedValue.asTestValue(), equalTo(3))
      assertThat(signingConfig.parsedValue.asTestValue(), nullValue())
      assertThat(versionNameSuffix.parsedValue.asTestValue(), equalTo("new_vsuffix"))
      assertThat(zipAlignEnabled.parsedValue.asTestValue(), equalTo(false))

      assertThat(matchingFallbacks.map { it.resolved.asTestValue() }, equalTo<List<String?>>(listOf(null)))
      assertThat(matchingFallbacks.map { it.parsedValue.asTestValue() }, equalTo<List<String?>>(listOf("debug")))
      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[0].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[0].parsedValue.asUnparsedValue(), equalTo("getDefaultProguardFile('proguard-android.txt')"))
      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[1].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[1].parsedValue.asTestValue(), equalTo(File("a.txt")))
      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[2].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[2].parsedValue.asTestValue(), equalTo(File("z.txt")))

      assertThat(manifestPlaceholders.parsedValue.asTestValue(), equalTo(mapOf()))

      if (afterSync) {
        assertThat(applicationIdSuffix.parsedValue.asTestValue(), equalTo(applicationIdSuffix.resolved.asTestValue()))
        assertThat(debuggable.parsedValue.asTestValue(), equalTo(debuggable.resolved.asTestValue()))
        assertThat(jniDebuggable.parsedValue.asTestValue(), equalTo(jniDebuggable.resolved.asTestValue()))
        assertThat(minifyEnabled.parsedValue.asTestValue(), equalTo(minifyEnabled.resolved.asTestValue()))
        assertThat(multiDexEnabled.parsedValue.asTestValue(), equalTo(multiDexEnabled.resolved.asTestValue()))
        assertThat(renderscriptDebuggable.parsedValue.asTestValue(), equalTo(renderscriptDebuggable.resolved.asTestValue()))
        assertThat(renderscriptOptimLevel.parsedValue.asTestValue(), equalTo(renderscriptOptimLevel.resolved.asTestValue()))
        // TODO(b/79142681) signingConfig resolved value is always null.
        assertThat(versionNameSuffix.parsedValue.asTestValue(), equalTo(versionNameSuffix.resolved.asTestValue()))
        assertThat(zipAlignEnabled.parsedValue.asTestValue(), equalTo(zipAlignEnabled.resolved.asTestValue()))
        // Note: Resolved values of matchingFallbacks property are not available.
        // TODO(b/72814329): assertThat(proGuardFiles[1].parsedValue.asTestValue(), equalTo(proGuardFiles[1].resolved.asTestValue()))
        // TODO(b/72814329): assertThat(proGuardFiles[2].parsedValue.asTestValue(), equalTo(proGuardFiles[2].resolved.asTestValue()))

        // Note: empty manifestPlaceholders does not match null value.
        assertThat(manifestPlaceholders.resolved.asTestValue(), equalTo(mapOf()))
       }
    }

    verifyValues(buildType)

    appModule.applyChanges()
    requestSyncAndWait()
    project = PsProjectImpl(resolvedProject).also { it.testResolve() }
    appModule = project.findModuleByName("app") as PsAndroidModule
    // Verify nothing bad happened to the values after the re-parsing.
    verifyValues(appModule.findBuildType("release")!!, afterSync = true)
  }

  fun testSetProperties_undeclaredDebug() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    var project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    var appModule = project.findModuleByName("app") as PsAndroidModule
    assertThat(appModule, notNullValue())

    val buildType = appModule.findBuildType("debug")
    assertThat(buildType, notNullValue()); buildType!!
    assertThat(buildType.isDeclared, equalTo(false))


    buildType.jniDebuggable = true.asParsed()

    fun verifyValues(buildType: PsBuildType, afterSync: Boolean = false) {
      val jniDebuggable = PsBuildType.BuildTypeDescriptors.jniDebuggable.bind(buildType).getValue()

      assertThat(jniDebuggable.parsedValue.asTestValue(), equalTo(true))

      if (afterSync) {
        assertThat(jniDebuggable.parsedValue.asTestValue(), equalTo(jniDebuggable.resolved.asTestValue()))
       }
    }

    verifyValues(buildType)

    appModule.applyChanges()
    requestSyncAndWait()
    project = PsProjectImpl(resolvedProject).also { it.testResolve() }
    appModule = project.findModuleByName("app") as PsAndroidModule
    // Verify nothing bad happened to the values after the re-parsing.
    verifyValues(appModule.findBuildType("debug")!!, afterSync = true)
  }

  fun testEditLists_undeclaredDebug() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    var project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    var appModule = project.findModuleByName("app") as PsAndroidModule
    assertThat(appModule, notNullValue())

    val buildType = appModule.findBuildType("debug")
    assertThat(buildType, notNullValue()); buildType!!

    PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).run {
      addItem(0).setParsedValue(File("z.txt").asParsed())
    }


    fun verifyValues(buildType: PsBuildType, afterSync: Boolean = false) {
      val proGuardFiles = PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).getEditableValues().map { it.getValue() }

      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[0].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[0].parsedValue.asTestValue(), equalTo(File("z.txt")))

      if (afterSync) {
        // TODO(b/72814329): assertThat(proGuardFiles[0].parsedValue.asTestValue(), equalTo(proGuardFiles[0].resolved.asTestValue()))
       }
    }

    verifyValues(buildType)

    appModule.applyChanges()
    requestSyncAndWait()
    project = PsProjectImpl(resolvedProject).also { it.testResolve() }
    appModule = project.findModuleByName("app") as PsAndroidModule
    // Verify nothing bad happened to the values after the re-parsing.
    verifyValues(appModule.findBuildType("debug")!!, afterSync = true)
  }

  fun testEditMaps_undeclaredDebug() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    var project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    var appModule = project.findModuleByName("app") as PsAndroidModule
    assertThat(appModule, notNullValue())

    val buildType = appModule.findBuildType("debug")
    assertThat(buildType, notNullValue()); buildType!!

    PsBuildType.BuildTypeDescriptors.manifestPlaceholders.bind(buildType).run {
      addEntry("k").setParsedValue("v".asParsed<Any>())
    }


    fun verifyValues(buildType: PsBuildType, afterSync: Boolean = false) {
      val manifestPlaceholders =
        PsBuildType.BuildTypeDescriptors.manifestPlaceholders.bind(buildType).getEditableValues().mapValues { it.value.getValue() }

      assertThat(manifestPlaceholders["k"]?.parsedValue?.asTestValue(), equalTo<Any>("v"))

      if (afterSync) {
        assertThat(manifestPlaceholders["k"]?.parsedValue?.asTestValue(), equalTo(manifestPlaceholders["k"]?.resolved?.asTestValue()))
       }
    }

    verifyValues(buildType)

    appModule.applyChanges()
    requestSyncAndWait()
    project = PsProjectImpl(resolvedProject).also { it.testResolve() }
    appModule = project.findModuleByName("app") as PsAndroidModule
    // Verify nothing bad happened to the values after the re-parsing.
    verifyValues(appModule.findBuildType("debug")!!, afterSync = true)
  }

  fun testInsertingProguardFiles() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    var project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    var appModule = project.findModuleByName("app") as PsAndroidModule
    assertThat(appModule, notNullValue())

    val buildType = appModule.findBuildType("release")
    assertThat(buildType, notNullValue()); buildType!!

    PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).run {
      val editableProGuardFiles = getEditableValues()
      editableProGuardFiles[1].setParsedValue(File("a.txt").asParsed())
      editableProGuardFiles[2].setParsedValue(File("b.txt").asParsed())
      addItem(0).setParsedValue(File("z.txt").asParsed())
    }


    fun verifyValues(buildType: PsBuildType, afterSync: Boolean = false) {
      val proGuardFiles = PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).getEditableValues().map { it.getValue() }

      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[0].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[0].parsedValue.asTestValue(), equalTo(File("z.txt")))
      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[1].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[1].parsedValue.asUnparsedValue(), equalTo("getDefaultProguardFile('proguard-android.txt')"))
      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[2].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[2].parsedValue.asTestValue(), equalTo(File("a.txt")))
      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[3].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[3].parsedValue.asTestValue(), equalTo(File("b.txt")))

      if (afterSync) {
        // TODO(b/72814329): assertThat(proGuardFiles[0].parsedValue.asTestValue(), equalTo(proGuardFiles[1].resolved.asTestValue()))
        // TODO(b/72814329): assertThat(proGuardFiles[2].parsedValue.asTestValue(), equalTo(proGuardFiles[2].resolved.asTestValue()))
        // TODO(b/72814329): assertThat(proGuardFiles[3].parsedValue.asTestValue(), equalTo(proGuardFiles[1].resolved.asTestValue()))
       }
    }

    verifyValues(buildType)

    appModule.applyChanges()
    requestSyncAndWait()
    project = PsProjectImpl(resolvedProject).also { it.testResolve() }
    appModule = project.findModuleByName("app") as PsAndroidModule
    // Verify nothing bad happened to the values after the re-parsing.
    verifyValues(appModule.findBuildType("release")!!, afterSync = true)
  }

  @Ignore("b/72853928")
  fun /*test*/SetListReferences() {
    loadProject(TestProjectPaths.PSD_SAMPLE)

    val resolvedProject = myFixture.project
    var project = PsProjectImpl(resolvedProject).also { it.testResolve() }

    var appModule = project.findModuleByName("app") as PsAndroidModule
    assertThat(appModule, notNullValue())

    val buildType = appModule.findBuildType("release")
    assertThat(buildType, notNullValue()); buildType!!

    PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).setParsedValue(
      ParsedValue.Set.Parsed(
        dslText = DslText.Reference("varProGuardFiles"),
        value = null
      )
    )

    fun verifyValues(buildType: PsBuildType, afterSync: Boolean = false) {
      val proGuardFilesValue = PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).getValue()
      val parsedProGuardFilesValue = proGuardFilesValue.parsedValue.value as? ParsedValue.Set.Parsed
      val proGuardFiles = PsBuildType.BuildTypeDescriptors.proGuardFiles.bind(buildType).getEditableValues().map { it.getValue() }

      assertThat(parsedProGuardFilesValue?.dslText, equalTo<DslText?>(DslText.Reference("varProGuardFiles")))

      assertThat(proGuardFiles.size, equalTo(2))
      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[0].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[0].parsedValue.asTestValue(), equalTo(File("proguard-rules.txt")))

      // TODO(b/72814329): Resolved values are not yet supported on list properties.
      assertThat(proGuardFiles[1].resolved.asTestValue(), nullValue())
      assertThat(proGuardFiles[1].parsedValue.asTestValue(), equalTo(File("proguard-rules2.txt")))

      if (afterSync) {
        // TODO(b/72814329): assertThat(proGuardFiles[0].parsedValue.asTestValue(), equalTo(proGuardFiles[0].resolved.asTestValue()))
        // TODO(b/72814329): assertThat(proGuardFiles[1].parsedValue.asTestValue(), equalTo(proGuardFiles[1].resolved.asTestValue()))
      }
    }

    verifyValues(buildType)

    appModule.applyChanges()
    requestSyncAndWait()
    project = PsProjectImpl(resolvedProject).also { it.testResolve() }
    appModule = project.findModuleByName("app") as PsAndroidModule
    // Verify nothing bad happened to the values after the re-parsing.
    verifyValues(appModule.findBuildType("release")!!, afterSync = true)
  }
}
