package mati.nave.land.tiles

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import kotlin.properties.Delegates

class Wall() : Actor(), Tile {
    companion object Static {
        private var tex: Texture by Delegates.notNull<Texture>()

        fun init(game: Game) {
            tex = game.astManager["Wall", Texture::class]
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
    }

    override fun setBounds(x: Float, y: Float, width: Float, height: Float) {
        super.setBounds(x, y, width, height)
    }

    override fun isCollisionable(): Boolean = true

    override fun doesHurt(): Boolean = false
}
