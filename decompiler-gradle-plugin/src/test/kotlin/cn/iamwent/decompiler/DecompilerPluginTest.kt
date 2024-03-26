package cn.iamwent.decompiler

import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DecompilerPluginTest {

    @Test
    fun pluginTest() {
        val pluginId = "cn.iamwent.decompiler.decompiler-gradle-plugin"
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(pluginId)

        assertTrue(project.pluginManager.hasPlugin(pluginId))
    }
}
