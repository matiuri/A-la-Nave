package mati.nave.mobs

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import mati.nave.land.Land
import mati.nave.land.objects.LObject
import mati.nave.land.tiles.Tile
import kotlin.properties.Delegates

class Player(private val tiles: Array<Array<out Land>>, private val objects: Array<Array<out Land>>) : Actor() {
    companion object Static {
        private var tex: Texture by Delegates.notNull<Texture>()

        fun init(game: Game) {
            tex = game.astManager["Player", Texture::class]
        }
    }

    var xI = 0
    var yI = 0
    var xMov: Int = 0
    var yMov: Int = 0

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
    }

    override fun act(delta: Float) {
        if (xMov < 0) {
            val tile = tiles[xI - 1][yI]
            if (tile is Tile && !tile.isCollisionable()) {
                xI--
                x = (tile as Actor).x
                (objects[xI][yI] as LObject).perform()
            }
            xMov = 0
        } else if (xMov > 0) {
            val tile = tiles[xI + 1][yI]
            if (tile is Tile && !tile.isCollisionable()) {
                xI++
                x = (tile as Actor).x
                (objects[xI][yI] as LObject).perform()
            }
            xMov = 0
        }

        if (yMov < 0) {
            val tile = tiles[xI][yI - 1]
            if (tile is Tile && !tile.isCollisionable()) {
                yI--
                y = (tile as Actor).y
                (objects[xI][yI] as LObject).perform()
            }
            yMov = 0
        } else if (yMov > 0) {
            val tile = tiles[xI][yI + 1]
            if (tile is Tile && !tile.isCollisionable()) {
                yI++
                y = (tile as Actor).y
                (objects[xI][yI] as LObject).perform()
            }
            yMov = 0
        }
    }
}
