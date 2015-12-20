package mati.nave.input

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import mati.nave.mobs.Player

class PlayerInputDesktop(private val player: Player) : InputListener() {
    override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.DOWN -> {
                player.yMov = -1
                return true
            }
            Input.Keys.UP -> {
                player.yMov = 1
                return true
            }
            Input.Keys.RIGHT -> {
                player.xMov = 1
                return true
            }
            Input.Keys.LEFT -> {
                player.xMov = -1
                return true
            }
            else -> return false
        }
    }
}
