package mati.nave.input

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import mati.nave.gui.PlanetButton

class PlanetButtonInput(private val planet: PlanetButton) : InputListener() {
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        planet.perform()
        return true
    }
}
