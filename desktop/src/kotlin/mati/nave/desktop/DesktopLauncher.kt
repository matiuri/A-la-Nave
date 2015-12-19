@file:JvmName("DesktopLauncher")

package mati.nave.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import mati.advancedgdx.AdvancedGame
import mati.nave.Game

fun main(arg: Array<String>) {
    val cfg = LwjglApplicationConfiguration()
    cfg.title = "A la Nave"
    cfg.width = 800
    cfg.height = 480
    cfg.resizable = false
    cfg.vSyncEnabled = true
    LwjglApplication(Game(), cfg)
}
