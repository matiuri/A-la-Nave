package mati.nave.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game
import kotlin.properties.Delegates

class Lock(id: Int) : Actor() {
    companion object Static {
        private var tex: Texture by Delegates.notNull<Texture>()

        fun init(game: Game) {
            tex = game.astManager["Lock", Texture::class]
        }
    }

    val bb: Rectangle = Rectangle()

    val batchColor: Color

    init {
        when (id) {
            1 -> batchColor = Color.RED
            2 -> batchColor = Color.YELLOW
            3 -> batchColor = Color.BLUE
            4 -> batchColor = Color.GREEN
            else -> batchColor = Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1f)
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.color = batchColor
        batch?.draw(tex, x, y, width, height)
        batch?.color = Color.WHITE
    }

    override fun setBounds(x: Float, y: Float, width: Float, height: Float) {
        super.setBounds(x, y, width, height)
        bb.set(x, y, width, height)
    }
}
