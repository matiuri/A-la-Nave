package mati.nave.land.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.scenes.scene2d.Actor

class Food(private val tex: TextureRegion) : Actor() {
    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(tex, x, y, width, height)
    }
}
