package mati.nave.land.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game

class Food(game: Game) : Actor() {
    private val tex: Texture

    init {
        val rnd: Float = MathUtils.random()
        if (rnd < 1 / 6f) {
            tex = game.astManager["Apple", Texture::class]
        } else if (rnd < 2 / 6f) {
            tex = game.astManager["Banana", Texture::class]
        } else if (rnd < 3 / 6f) {
            tex = game.astManager["Cherries", Texture::class]
        } else if (rnd < 4 / 6f) {
            tex = game.astManager["Lemon", Texture::class]
        } else if (rnd < 5 / 6f) {
            tex = game.astManager["Orange", Texture::class]
        } else {
            tex = game.astManager["Watermelon", Texture::class]
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
    }
}
