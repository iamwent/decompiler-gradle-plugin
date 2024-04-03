package io.github.iamwent.decompiler

import org.gradle.api.DefaultTask
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.withType
import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

/**
 * A task that decompile .class to .java
 */
open class DecompileTask : DefaultTask() {

    /**
     * Enable decompiling Jetpack Compose related files, default is enabled
     */
    @Input
    var compose: Boolean = true
        get() = System.getProperty("compose")?.toBoolean() ?: field

    /**
     * Enable decompiling Kotlin Coroutine related files, default is enabled
     */
    @Input
    var coroutine: Boolean = true
        get() = System.getProperty("coroutine")?.toBoolean() ?: field

    @TaskAction
    fun decompile() {
        project.tasks.withType<KotlinCompile>()
            .firstOrNull { it.name == KOTLIN_COMPILE_TASK }
            ?.let { decompile(it) }
    }

    private fun decompile(compile: KotlinCompile) {
        val files = compile.destinationDirectory.asFileTree.files
            .filter { it.extension == CLASS_EXTENSION }
            .map { it.absolutePath }
        if (files.isEmpty()) {
            logger.log(LogLevel.DEBUG, "No class file found.")
            return
        }

        logger.log(LogLevel.DEBUG, "Decompile: start, compose=$compose, coroutine=$coroutine")

        val output = File(project.layout.buildDirectory.asFile.get(), "decompiled").apply {
            deleteRecursively()
            mkdirs()
        }
        val options = (files + output.absolutePath).toTypedArray()
        ConsoleDecompiler.main(options)

        output.listFiles()
            ?.filterNot {
                val text = it.readText()
                return@filterNot when {
                    compose && text.contains("androidx.compose") -> true
                    coroutine && text.contains("kotlinx.coroutines") -> true
                    else -> false
                }
            }?.forEach {
                it.delete()
            }
        logger.log(LogLevel.DEBUG, "Decompile: completed, decompiled in ${output.path}")
    }

    companion object {
        internal const val KOTLIN_COMPILE_TASK = "compileDebugKotlin"
        internal const val TASK_NAME = "decompile"
        private const val CLASS_EXTENSION = "class"
    }
}
