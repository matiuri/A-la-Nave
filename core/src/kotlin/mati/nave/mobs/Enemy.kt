package mati.nave.mobs

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.advancedgdx.graphics.Animation
import mati.advancedgdx.utils.split
import mati.nave.Game
import kotlin.properties.Delegates

class Enemy(private val xAxis: Boolean, private val origin: Float, private val max: Int,
            private val speed: Float) : Actor() {
    companion object Static {
        private var animation: Animation by Delegates.notNull<Animation>()
        var player: Player by Delegates.notNull<Player>()

        fun init(game: Game) {
            val tex: Texture = game.astManager["Player", Texture::class]
            animation = Animation(tex.split(29), 0.005f, true)
        }
    }

    val bb: Rectangle = Rectangle()
    var positive: Boolean = true
    var timer: Float = speed

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.color = Color.RED
        batch?.draw(animation.get(), x, y, width, height)
        batch?.color = Color.WHITE
    }

    override fun act(delta: Float) {
        animation.update(delta)
        if (timer > 0) {
            timer -= delta
        } else {
            if (xAxis) {
                if (Math.abs(x - origin) > max) positive = !positive
                if (positive) setBounds(x + 32, y, width, height)
                else setBounds(x - 32, y, width, height)
            } else {
                if (Math.abs(y - origin) > max) positive = !positive
                if (positive) setBounds(x, y + 32, width, height)
                else setBounds(x, y - 32, width, height)
            }
            timer = speed
        }
    }

    override fun setBounds(x: Float, y: Float, width: Float, height: Float) {
        super.setBounds(x, y, width, height)
        bb.set(x, y, width, height)
    }
}
