plugins {
    `java-gradle-plugin`
    `maven-publish`
    `kotlin-dsl`
    kotlin("jvm")
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = PluginInfo.GROUP
version = PluginInfo.VERSION_NAME

dependencies {
    compileOnly(gradleApi())
    implementation(libs.kotlin.gradle.plugin)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation(libs.junit.jupiter)
    testImplementation(gradleTestKit())
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    from(configurations.compileClasspath.get()
        .filter { it.path.contains("java-decompiler-engine") }
        .map { if (it.isDirectory) it else zipTree(it) })
}

publishing {
    publications {
        create<MavenPublication>("MavenPublish") {
            group = PluginInfo.GROUP
            artifactId = PluginInfo.ARTIFACT_ID
            version = PluginInfo.VERSION_NAME

            pom {
                name.set(PluginInfo.POM_NAME)
                description.set(PluginInfo.POM_DESCRIPTION)
                url.set(PluginInfo.POM_URL)

                scm {
                    url.set(PluginInfo.POM_SCM_URL)
                    connection.set(PluginInfo.POM_SCM_CONNECTION)
                    developerConnection.set(PluginInfo.POM_SCM_DEV_CONNECTION)
                }

                licenses {
                    license {
                        name.set(PluginInfo.POM_LICENSE_NAME)
                        url.set(PluginInfo.POM_LICENSE_URL)
                        distribution.set(PluginInfo.POM_LICENSE_DIST)
                    }
                }

                developers {
                    developer {
                        id.set("iamwent")
                        name.set("iamwent")
                        email.set("ilovewent@gmail.com")
                    }
                    developer {
                        id.set("takahirom")
                        name.set("Takahiro Menju")
                        email.set("takam.dev@gmail.com")
                    }
                }

                issueManagement {
                    system = PluginInfo.POM_ISSUE_SYSTEM
                    url = PluginInfo.POM_ISSUE_URL
                }
            }
        }
        repositories {
            maven {
                name = "localPluginRepository"
                url = uri("build/repository")
            }
            mavenLocal()
        }
    }
}

project(":decompiler-gradle-plugin") {
    version = PluginInfo.VERSION_NAME
}

gradlePlugin {
    website.set(PluginInfo.POM_URL)
    vcsUrl.set(PluginInfo.POM_URL)

    plugins {
        create("decompilerGradlePlugin") {
            id = PluginInfo.GROUP
            implementationClass = PluginInfo.IMPLEMENTATION_CLASS
            displayName = PluginInfo.POM_NAME
            description = PluginInfo.POM_DESCRIPTION
            tags.set(listOf("decompile", "jetpack compose", "kotlin coroutine"))
        }
    }
}

object PluginInfo {
    const val GROUP = "io.github.iamwent.decompiler"
    const val ARTIFACT_ID = "decompiler-gradle-plugin"
    const val IMPLEMENTATION_CLASS = "io.github.iamwent.decompiler.DecompilerPlugin"
    const val VERSION_NAME = "0.1.0"

    const val POM_NAME = "Decompiler Gradle Plugin"
    const val POM_DESCRIPTION =
        "A Gradle plugin for decompiling bytecodes into Java codes. It helps us dive into Jetpack Compose, Kotlin Coroutine, and more."
    const val POM_URL = "https://github.com/iamwent/decompiler-gradle-plugin"

    const val POM_SCM_URL = "https://github.com/iamwent/decompiler-gradle-plugin"
    const val POM_SCM_CONNECTION = "scm:git:git://github.com/iamwent/decompiler-gradle-plugin.git"
    const val POM_SCM_DEV_CONNECTION =
        "scm:git:ssh://git@github.com/iamwent/decompiler-gradle-plugin.git"

    const val POM_LICENSE_NAME = "The Apache Software License, Version 2.0"
    const val POM_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val POM_LICENSE_DIST = "repo"

    const val POM_ISSUE_SYSTEM = "github"
    const val POM_ISSUE_URL = "https://github.com/iamwent/decompiler-gradle-plugin/issues"
}
