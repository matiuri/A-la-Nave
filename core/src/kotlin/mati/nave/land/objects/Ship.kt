package mati.nave.land.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import kotlin.properties.Delegates

class Ship() : Actor(), LObject {
    companion object Static {
        private var tex: Texture by Delegates.notNull<Texture>()
        private var game: Game by Delegates.notNull<Game>()

        fun init(game: Game) {
            tex = game.astManager["Ship", Texture::class]
            this.game = game
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
    }

    override fun setBounds(x: Float, y: Float, width: Float, height: Float) {
        super.setBounds(x, y, width * 3, height * 3)
    }

    override fun perform() {
        game.scrManager.change("Title")
    }

    class ShipC() : LObject {
        override fun perform() {
            game.scrManager.change("Title")
        }

        override fun setBounds(x: Float, y: Float, width: Float, height: Float) {
        }

    }
}
