package com.github.rocketraman

import org.gradle.api.Plugin
import org.gradle.api.Project

class DepExcludesPlugin implements Plugin<Project> {
  void apply(final Project project) {
    project.ext.depExcept = { dep, excludes=[] ->
      return project.dependencies.create(dep) {
        for(e in excludes) exclude module: e
      }
    }
    project.ext.scalaDepExcept = { dep, excludes=[] ->
      return project.depExcept(dep, (["scala-compiler", "scala-library", "scala-reflect"] << excludes).flatten())
    }
  }
}
