package com.veek.wjuh.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.veek.wjuh.Wjuh

object DesktopLauncher {
    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = Wjuh.WIDTH
        config.height = Wjuh.HEIGHT
        config.title = Wjuh.TITLE
        config.resizable = false
        LwjglApplication(Wjuh(), config)
    }
}
