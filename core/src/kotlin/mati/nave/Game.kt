package mati.nave

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import mati.advancedgdx.AdvancedGame
import mati.advancedgdx.assets.FontLoader.FontLoaderParameter
import mati.nave.screens.GameScreen
import mati.nave.screens.TitleScreen

class Game() : AdvancedGame() {
    override fun create() {
        super.create()
        init(this)
        Gdx.app.logLevel = Application.LOG_DEBUG
        astManager.queue("ButtonUp", "GUI/ButtonUp.png", Texture::class).queue("ButtonDown", "GUI/ButtonDown.png",
                Texture::class).queue("UbuntuRGen", "fonts/Ubuntu-R.ttf", FreeTypeFontGenerator::class)
                .queue("UbuntuR-64", "ubuntur64", BitmapFont::class, FontLoaderParameter(astManager["UbuntuRGen"]) {
                    it.color = Color.WHITE
                    it.borderColor = Color.BLACK
                    it.borderWidth = 2.5f
                    it.size = 64
                }).queue("Path", "tiles/Path.png", Texture::class).queue("Wall", "tiles/Wall.png", Texture::class)
                .queue("Ship", "tiles/Ship.png", Texture::class).queue("Player", "mobs/Player.png", Texture::class)
                .queue("Apple", "tiles/Apple.png", Texture::class).queue("Banana", "tiles/Banana.png", Texture::class)
                .queue("Cherries", "tiles/Cherries.png", Texture::class)
                .queue("Lemon", "tiles/Lemon.png", Texture::class).queue("Orange", "tiles/Orange.png", Texture::class)
                .queue("Watermelon", "tiles/Watermelon.png", Texture::class)
                .load {
                    scrManager.add("Title", TitleScreen(this)).load("Title").add("Game", GameScreen(this)).load("Game")
                            .change("Title")
                }
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f)
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        super.render()
    }
}
