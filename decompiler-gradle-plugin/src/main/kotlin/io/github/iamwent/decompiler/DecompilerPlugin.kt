package io.github.iamwent.decompiler

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.kotlin.dsl.withType
import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

class DecompilerPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.withType<KotlinCompile>()
            .whenTaskAdded {
                doLast {
                    val kotlinFiles = destinationDirectory.asFileTree.files.map { it.absolutePath }
                    val output = File(project.layout.buildDirectory.asFile.get(), "decompiled").apply {
                        deleteRecursively()
                        mkdir()
                    }
                    val options = (kotlinFiles + output.absolutePath).toTypedArray()
                    ConsoleDecompiler.main(options)

                    output.listFiles()
                        ?.filter { !it.readText().contains("androidx.compose") }
                        ?.forEach {
                            it.delete()
                        }
                    logger.log(LogLevel.LIFECYCLE, "DecompilerPlugin: decompiled in ${output.path}")
                }
            }
    }
}
