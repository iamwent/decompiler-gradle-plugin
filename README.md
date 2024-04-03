# Decompiler Gradle Plugin
A Gradle plugin for decompiling bytecodes into Java codes. It helps us dive into Jetpack Compose, Kotlin Coroutine, and more.

The plugin was developed for research and learning purposes, utilizing the [Java Decompiler Engine](https://github.com/JetBrains/intellij-community/tree/master/plugins/java-decompiler/engine).


## Installation
1. In your project's root `build.gradle` file:

Groovy:
```groovy
buildscript {
    dependencies {
        classpath "cn.iamwent.decompiler.decompiler-gradle-plugin:decompiler-gradle-plugin:0.1.0"
    }
}
```

Kotlin:
```kotlin
buildscript {
    dependencies {
        classpath("io.github.iamwent.decompiler:decompiler-gradle-plugin:0.1.0")
    }
}
```

2. In your app-level `build.gradle` file:

Groovy:
```groovy
plugins {
    id 'io.github.iamwent.decompiler'
}
```

Kotlin:
```groovy
plugins {
    id("io.github.iamwent.decompiler")
}
```

3. Run the command, and check the Java codes in: `app/build/decompiled`
```shell
./gradlew :app:compileDebugKotlin
```

## Credit
* IntelliJ IDEA: [Java Decompiler Engine](https://github.com/JetBrains/intellij-community/tree/master/plugins/java-decompiler/engine)
* takahirom: [decomposer](https://github.com/takahirom/decomposer)

## License
Apache 2.0. See [LICENSE](LICENSE) for more information.
