package mati.nave

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import mati.advancedgdx.AdvancedGame
import mati.advancedgdx.assets.FontLoader.FontLoaderParameter
import mati.nave.screens.TitleScreen
import kotlin.properties.Delegates

class Game() : AdvancedGame() {
    override fun create() {
        super.create()
        init(this)
        astManager.queue("ButtonUp", "GUI/ButtonUp.png", Texture::class).queue("ButtonDown", "GUI/ButtonDown.png",
                Texture::class).queue("UbuntuRGen", "fonts/Ubuntu-R.ttf", FreeTypeFontGenerator::class)
                .queue("UbuntuR-64", "ubuntur64", BitmapFont::class, FontLoaderParameter(astManager["UbuntuRGen"]) {
                    it.color = Color.WHITE
                    it.borderColor = Color.BLACK
                    it.borderWidth = 2.5f
                    it.size = 64
                }).load {
            scrManager.add("Title", TitleScreen(this)).load("Title").change("Title")
        }
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f)
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        super.render()
    }
}
