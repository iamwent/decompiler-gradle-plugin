buildscript {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath ("io.github.iamwent.decompiler:decompiler-gradle-plugin:0.1.0")
    }
}
