buildscript {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.3.1")
        classpath(libs.kotlin.gradle.plugin)
        classpath ("cn.iamwent.decompiler.decompiler-gradle-plugin:decompiler-gradle-plugin:0.1.0")
    }
}
