package mati.nave.mobs

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import mati.nave.land.Land
import mati.nave.land.objects.LObject
import mati.nave.land.tiles.Tile
import mati.advancedgdx.AdvancedGame.Static.log
import kotlin.properties.Delegates

class Player(private val tiles: Array<Array<out Land>>, private val objects: Array<Array<out Land>>) : Actor() {
    companion object Static {
        private var tex: Texture by Delegates.notNull<Texture>()
        private var game: Game by Delegates.notNull<Game>()

        fun init(game: Game) {
            tex = game.astManager["Player", Texture::class]
            this.game = game
        }
    }

    val maxMoves: Int = 7
    var moves: Int = maxMoves

    var xI: Int = 0
    var yI: Int = 0
    var xMov: Int = 0
    var yMov: Int = 0

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
    }

    override fun act(delta: Float) {
        if (moves > 0) {
            if (xMov < 0) {
                val tile = tiles[xI - 1][yI]
                if (tile is Tile && !tile.isCollisionable()) {
                    xI--
                    x = (tile as Actor).x
                    reduce()
                    (objects[xI][yI] as LObject).perform()
                }
                xMov = 0
            } else if (xMov > 0) {
                val tile = tiles[xI + 1][yI]
                if (tile is Tile && !tile.isCollisionable()) {
                    xI++
                    x = (tile as Actor).x
                    reduce()
                    (objects[xI][yI] as LObject).perform()
                }
                xMov = 0
            }

            if (yMov < 0) {
                val tile = tiles[xI][yI - 1]
                if (tile is Tile && !tile.isCollisionable()) {
                    yI--
                    y = (tile as Actor).y
                    reduce()
                    (objects[xI][yI] as LObject).perform()
                }
                yMov = 0
            } else if (yMov > 0) {
                val tile = tiles[xI][yI + 1]
                if (tile is Tile && !tile.isCollisionable()) {
                    yI++
                    y = (tile as Actor).y
                    reduce()
                    (objects[xI][yI] as LObject).perform()
                }
                yMov = 0
            }
        } else if (xMov != 0 || yMov != 0)
            game.scrManager.change("Title")
    }

    private fun reduce() {
        moves--
        log.d("${this.javaClass.simpleName}", "Moves left: $moves")
    }
}
