package mati.nave.land.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor

class Food(private val tex: TextureRegion) : Actor(){
    private var used: Boolean = false

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (!used)
            batch?.draw(tex, x, y, width, height)
    }
}
