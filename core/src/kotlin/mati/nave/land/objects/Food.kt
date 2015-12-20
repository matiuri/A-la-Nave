package mati.nave.land.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import mati.nave.mobs.Player
import kotlin.properties.Delegates

class Food() : Actor(), LObject {
    companion object Static {
        private var tex: Texture by Delegates.notNull<Texture>()
        private var game: Game by Delegates.notNull<Game>()
        var player: Player by Delegates.notNull<Player>()

        fun init(game: Game) {
            tex = game.astManager["Food", Texture::class]
            this.game = game
        }
    }

    private var used: Boolean = false

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (!used)
            batch?.draw(tex, x, y, width, height)
    }

    override fun perform() {
        if (!used) {
            player.moves = player.maxMoves
            used = true
        }
    }
}
