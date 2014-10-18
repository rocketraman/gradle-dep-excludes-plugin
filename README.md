# Gradle Dependency Exclude Syntax Sugar Plugin

Sometimes you just want a simple way to exclude dependencies without creating closures. This plugin
creates a method on a Gradle project that provides a little syntax sugar for exclusions.

## Usage

This plugin is available from [Bintray's JCenter repository](http://jcenter.bintray.com/). You can add it to your build script using
the following configuration:

```groovy
apply plugin: 'com.github.rocketraman.dep-excludes'

buildscript {
  repositories {
    jcenter()
  }
  
  dependencies {
    classpath 'com.github.rocketraman:dep-excludes:0.1'
  }
}
```
The current version is known to work with Gradle versions up to 2.1.

### Simple Exclusions

To create a dependency org.foo:foo:1.0 with some simple exclusions for bar and baz:

`depExcept("org.foo:foo:1.0", ["bar", "baz"])`

### Scala Exclusions

Scala libraries often declare dependencies on scala-compiler, scala-library, and scala-reflect, but with
versions that are not the same as those declared in the project. These could be excluded explicitly using
`depExcept`, but some more syntax sugar is provided for these cases:

`scalaDepExcept("org.foo:foo_2.11:1.0")`

will create the foo_2.11 dependency, but exclude the scala libs, and 

`scalaDepExcept("org.foo:foo_2.11:1.0", ["bar", "baz"])`

will create the foo_2.11 dependency, but exclude the scala libs as well as `bar` and `baz`.
