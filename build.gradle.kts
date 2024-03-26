plugins {
    `kotlin-dsl`
    java
    id("java-gradle-plugin")
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    google()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
}

tasks.jar {
    from(configurations.compileClasspath.get().filter{
        it.path.contains("fernflower.jar")
    }.map { if (it.isDirectory()) it else zipTree(it) })
}


gradlePlugin {
    plugins {
        create("decomposer") {
            id = "com.github.takahirom.decomposer"
            implementationClass = "com.github.takahirom.decomposer.DecomposerPlugin"
        }
    }
}
