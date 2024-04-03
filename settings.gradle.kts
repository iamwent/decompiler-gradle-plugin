pluginManagement {
    repositories {
        google()
        mavenLocal()
        maven(url = "./plugin/build/repository")
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "decompiler-gradle-plugin"
include(":decompiler-gradle-plugin")
include(":sample")
