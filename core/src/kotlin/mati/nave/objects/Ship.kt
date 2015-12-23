package mati.nave.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor

class Ship(private val tex: TextureRegion) : Actor() {
    val bb: Rectangle = Rectangle(0f, 0f, 0f, 0f)

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
    }

    override fun setBounds(x: Float, y: Float, width: Float, height: Float) {
        super.setBounds(x, y, width, height)
        bb.set(x, y, width, height)
    }
}
