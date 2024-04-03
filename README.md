# Decompiler Gradle Plugin
A Gradle plugin for decompiling bytecodes into Java codes. It helps us dive into Jetpack Compose, Kotlin Coroutine, and more.

The plugin was developed for research and learning purposes, utilizing the [Java Decompiler Engine](https://github.com/JetBrains/intellij-community/tree/master/plugins/java-decompiler/engine).


## Installation
1. Add the plugin to your top-level build script:

**`plugins`** block:

<details open>
<summary>Kotlin</summary>

```kotlin
plugins {
    id("io.github.iamwent.decompiler") version "0.2.0"
}
```
</details>

<details>
<summary>Groovy</summary>
```groovy
plugins {
  id "com.github.ben-manes.versions" version "$version"
}
```
</details>

or via the

**`buildscript`** block:

<details open>
<summary>Kotlin</summary>

```kotlin
buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("io.github.iamwent.decompiler:decompiler-gradle-plugin:$version")
    }
}
```
</details>

<details>
<summary>Groovy</summary>

```groovy
buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath "io.github.iamwent.decompiler:decompiler-gradle-plugin:$version"
    }
}
```
</details>

2. Apply the plugin to your app-level build script:

<details open>
<summary>Kotlin</summary>

```kotlin
plugins {
    id("io.github.iamwent.decompiler")
}
```
</details>

<details>
<summary>Groovy</summary>

```groovy
plugins {
    id 'io.github.iamwent.decompiler'
}
```
</details>

3. Run the `decompile` gradle task, and check the Java codes in: `app/build/decompiled`
```shell
./gradlew :app:decompile
```

## Credit
* IntelliJ IDEA: [Java Decompiler Engine](https://github.com/JetBrains/intellij-community/tree/master/plugins/java-decompiler/engine)
* takahirom: [decomposer](https://github.com/takahirom/decomposer)

## License
Apache 2.0. See [LICENSE](LICENSE) for more information.
