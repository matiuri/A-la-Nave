package mati.nave.mobs

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import kotlin.properties.Delegates

class Player(private val walls: com.badlogic.gdx.utils.Array<RectangleMapObject>) : Actor() {
    companion object Static {
        private var tex: Texture by Delegates.notNull<Texture>()
        private var game: Game by Delegates.notNull<Game>()

        fun init(game: Game) {
            tex = game.astManager["Player", Texture::class]
            this.game = game
        }
    }

    val bb: Rectangle = Rectangle(x, y, width, height)
    //val sr: ShapeRenderer = ShapeRenderer()

    val maxMoves: Int = 6
    var moves: Int = maxMoves

    var xI: Int = 0
    var yI: Int = 0
    var xMov: Int = 0
    var yMov: Int = 0

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
        batch?.end()
        //sr.begin(ShapeRenderer.ShapeType.Line)
        //sr.rect(bb.x, bb.y, bb.width, bb.height)
        //sr.end()
        batch?.begin()
    }

    override fun act(delta: Float) {
        if (moves > 0) {
            if (xMov > 0) {
                xMov = 0
                yMov = 0
                bb.set(x + 32, y, width, height)
                walls.forEach {
                    if (bb.overlaps(it.rectangle)) {
                        bb.set(x, y, width, height)
                        return
                    }
                }
                x += 32
                reduce()
            } else if (xMov < 0) {
                xMov = 0
                yMov = 0
                bb.set(x - 32, y, width, height)
                walls.forEach {
                    if (bb.overlaps(it.rectangle)) {
                        bb.set(x, y, width, height)
                        return
                    }
                }
                x -= 32
                reduce()
            }

            if (yMov > 0) {
                xMov = 0
                yMov = 0
                bb.set(x, y + 32, width, height)
                walls.forEach {
                    if (bb.overlaps(it.rectangle)) {
                        bb.set(x, y, width, height)
                        return
                    }
                }
                y += 32
                reduce()
            } else if (yMov < 0) {
                xMov = 0
                yMov = 0
                bb.set(x, y - 32, width, height)
                walls.forEach {
                    if (bb.overlaps(it.rectangle)) {
                        bb.set(x, y, width, height)
                        return
                    }
                }
                y -= 32
                reduce()
            }
        } else if (xMov != 0 || yMov != 0) {
            game.scrManager.change("Title") //TODO: Game Over
        }
    }

    private fun reduce() {
        //moves--
        //log.d("${this.javaClass.simpleName}", "Moves left: $moves")
    }
}
