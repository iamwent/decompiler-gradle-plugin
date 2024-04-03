package io.github.iamwent.decompiler

import org.gradle.api.Plugin
import org.gradle.api.Project

class DecompilerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val tasks = project.tasks
        if (!tasks.names.contains(DecompileTask.TASK_NAME)) {
            tasks.register(DecompileTask.TASK_NAME, DecompileTask::class.java) {
                group = "help"
                description = "A task that decompile .class to .java. It helps us dive into Jetpack Compose, Kotlin Coroutine, and more."
                dependsOn(DecompileTask.KOTLIN_COMPILE_TASK)
            }
        }
    }

}
