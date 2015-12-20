package mati.nave.input

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import mati.nave.mobs.Player

class PlayerInputDesktop(private val player: Player) : InputListener() {
    override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.DOWN -> {
                player.yMov = -15
                return true
            }
            Input.Keys.UP -> {
                player.yMov = 15
                return true
            }
            Input.Keys.RIGHT -> {
                player.xMov = 15
                return true
            }
            Input.Keys.LEFT -> {
                player.xMov = -15
                return true
            }
            else -> return false
        }
    }

    override fun keyUp(event: InputEvent?, keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.DOWN -> {
                player.yMov = 0
                return true
            }
            Input.Keys.UP -> {
                player.yMov = 0
                return true
            }
            Input.Keys.RIGHT -> {
                player.xMov = 0
                return true
            }
            Input.Keys.LEFT -> {
                player.xMov = 0
                return true
            }
            else -> return false
        }
    }
}
