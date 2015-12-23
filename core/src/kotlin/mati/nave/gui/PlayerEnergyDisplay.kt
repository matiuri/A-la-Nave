package mati.nave.gui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import mati.nave.mobs.Player
import kotlin.properties.Delegates

class PlayerEnergyDisplay(private val player: Player) : Actor() {
    companion object Static {
        private var full: Texture by Delegates.notNull<Texture>()
        private var empty: Texture by Delegates.notNull<Texture>()

        fun init(game: Game) {
            full = game.astManager["EnergyF", Texture::class]
            empty = game.astManager["EnergyE", Texture::class]
        }
    }

    private var energies: Array<Boolean> by Delegates.notNull<Array<Boolean>>()

    init {
        energies = Array(player.maxMoves) { true }
        setSize(20f * player.maxMoves, 20f)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        for (i in 0..energies.size - 2) {
            if (energies[i]) batch?.draw(full, x + 20 * i, y, 20f, 20f)
            else batch?.draw(empty, x + 20 * i, y, 20f, 20f)
        }
    }

    fun decrease() {
        for (i in (energies.size - 2) downTo 0) {
            if (energies[i]) {
                energies[i] = false
                break
            }
        }
    }

    fun recoverAll() {
        Gdx.app.postRunnable {
            for (i in energies.indices) {
                energies[i] = true
            }
        }
    }
}