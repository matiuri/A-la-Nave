package mati.nave.mobs

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import kotlin.properties.Delegates

class Player() : Actor() {
    companion object Static {
        private var tex: Texture by Delegates.notNull<Texture>()

        fun init(game: Game) {
            tex = game.astManager["Player", Texture::class]
        }
    }

    var xMov: Int = 0
    var yMov: Int = 0

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
    }

    override fun act(delta: Float) {
        x += xMov * delta
        y += yMov * delta
    }
}
