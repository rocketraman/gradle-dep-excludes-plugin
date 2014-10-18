package com.github.rocketraman

import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class DepExcludesPluginTest {

  @Test
  public void depExcludesPluginAddsExcludeMethodsToProject() {
    Project project = ProjectBuilder.builder().build()
    project.apply plugin: 'com.github.rocketraman.dep-excludes'

    def dep = project.depExcept("foo:bar:1.0")
    assertTrue(dep instanceof ModuleDependency)
    assertEquals(dep.excludeRules.size(), 0)

    dep = project.depExcept("foo:bar:1.0", ["baz"])
    assertTrue(dep instanceof ModuleDependency)
    assertEquals(dep.excludeRules.size(), 1)
    assertEquals(dep.excludeRules[0].module, "baz")

    dep = project.scalaDepExcept("foo:bar:1.0")
    assertTrue(dep instanceof ModuleDependency)
    assertEquals(dep.excludeRules.size(), 3)
    assertEquals(dep.excludeRules[0].module, "scala-compiler")
    assertEquals(dep.excludeRules[1].module, "scala-library")
    assertEquals(dep.excludeRules[2].module, "scala-reflect")

    dep = project.scalaDepExcept("foo:bar:1.0", ["baz", "jaz"])
    assertTrue(dep instanceof ModuleDependency)
    assertEquals(dep.excludeRules.size(), 5)
    assertEquals(dep.excludeRules[0].module, "scala-compiler")
    assertEquals(dep.excludeRules[1].module, "scala-library")
    assertEquals(dep.excludeRules[2].module, "scala-reflect")
    assertEquals(dep.excludeRules[3].module, "baz")
    assertEquals(dep.excludeRules[4].module, "jaz")
  }
}
