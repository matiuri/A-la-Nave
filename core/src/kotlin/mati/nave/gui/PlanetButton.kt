package mati.nave.gui

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.nave.Game

class PlanetButton(game: Game, private val lvl: Int, private val batchColor: Color, val perform: () -> Unit) : Actor() {
    private val tex: Texture = game.astManager["Planet", Texture::class]
    private val font: BitmapFont = game.astManager["UbuntuR-32", BitmapFont::class]

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.color = batchColor
        batch?.draw(tex, x, y, width, height)
        batch?.color = Color.WHITE
        font.draw(batch, "$lvl", x + width / 2, y + width / 2)
    }
}
