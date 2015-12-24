package mati.nave.mobs

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.advancedgdx.AdvancedGame.Static.log
import mati.advancedgdx.graphics.Animation
import mati.advancedgdx.utils.split
import mati.nave.Game
import mati.nave.gui.PlayerEnergyDisplay
import mati.nave.objects.Key
import mati.nave.objects.Lock
import kotlin.properties.Delegates

class Player(private val walls: com.badlogic.gdx.utils.Array<RectangleMapObject>, private val ship: Array<Rectangle>,
             private val map: TiledMap, private val pairs: MutableMap<TextureMapObject, Actor>) : Actor() {
    companion object Static {
        private var game: Game by Delegates.notNull<Game>()
        private var animation: Animation by Delegates.notNull<Animation>()

        fun init(game: Game) {
            val tex: Texture = game.astManager["Player", Texture::class]
            animation = Animation(tex.split(29), 0.15f, true)
            this.game = game
        }
    }

    val bb: Rectangle = Rectangle(x, y, width, height)

    val maxMoves: Int = 6
    var moves: Int = maxMoves

    var xI: Int = 0
    var yI: Int = 0
    var xMov: Int = 0
    var yMov: Int = 0

    var display: PlayerEnergyDisplay? = null

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(animation.get(), x, y, width, height)
    }

    override fun act(delta: Float) {
        animation.update(delta)
        if (moves > 0) {
            if (xMov > 0) {
                xMov = 0
                yMov = 0
                bb.set(x + 32, y, width, height)
                if (checkBounds()) return
                x += 32
                reduce()
            } else if (xMov < 0) {
                xMov = 0
                yMov = 0
                bb.set(x - 32, y, width, height)
                if (checkBounds()) return
                x -= 32
                reduce()
            }

            if (yMov > 0) {
                xMov = 0
                yMov = 0
                bb.set(x, y + 32, width, height)
                if (checkBounds()) return
                y += 32
                reduce()
            } else if (yMov < 0) {
                xMov = 0
                yMov = 0
                bb.set(x, y - 32, width, height)
                if (checkBounds()) return
                y -= 32
                reduce()
            }
        } else if (xMov != 0 || yMov != 0) {
            game.scrManager.change("Title") //TODO: Game Over
        }
    }

    private fun checkBounds(): Boolean {
        walls.forEach {
            if (bb.overlaps(it.rectangle)) {
                bb.set(x, y, width, height)
                return true
            }
        }
        ship.forEach {
            if (bb.overlaps(it)) {
                game.scrManager.change("Title") //TODO: WIN
            }
        }
        map.layers["Objects"].objects.getByType(TextureMapObject::class.java).forEach {
            if (it.properties["name", String::class.java].equals("food")) {
                if (bb.overlaps(Rectangle(it.x, it.y, it.textureRegion.regionWidth.toFloat(),
                        it.textureRegion.regionHeight.toFloat()))) {
                    moves = maxMoves
                    display?.recoverAll()
                    map.layers["Objects"].objects.remove(it)
                    pairs[it]?.remove()
                }
            } else if (it.properties["name", String::class.java].equals("lock")) {
                if (bb.overlaps((pairs[it] as Lock).bb)) {
                    bb.set(x, y, width, height)
                    return true
                }
            } else if (it.properties["name", String::class.java].equals("key")) {
                if (bb.overlaps((pairs[it] as Key).bb)) {
                    val id: String = it.properties["pair", String::class.java]
                    map.layers["Objects"].objects.getByType(TextureMapObject::class.java).forEach locks@ { lock ->
                        if (lock.properties["name", String::class.java].equals("lock") &&
                                lock.properties["pair", String::class.java].equals(id)) {
                            map.layers["Objects"].objects.remove(lock)
                            pairs[lock]?.remove()
                            map.layers["Objects"].objects.remove(it)
                            pairs[it]?.remove()
                            return@locks
                        }
                    }
                }
            }
        }
        return false
    }

    private fun reduce() {
        moves--
        display?.decrease()
        log.d("${this.javaClass.simpleName}", "Moves left: $moves")
    }
}
