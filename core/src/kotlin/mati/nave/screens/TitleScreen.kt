package mati.nave.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import mati.advancedgdx.screens.Screen
import mati.advancedgdx.AdvancedGame.Static.log
import mati.advancedgdx.utils.addListener1
import mati.advancedgdx.utils.createButton
import mati.advancedgdx.utils.createLabel
import mati.advancedgdx.utils.createNPD
import mati.nave.Game
import kotlin.properties.Delegates

class TitleScreen(game: Game) : Screen(game) {
    private var stage: Stage by Delegates.notNull<Stage>()
    private var up: NinePatchDrawable by Delegates.notNull<NinePatchDrawable>()
    private var down: NinePatchDrawable by Delegates.notNull<NinePatchDrawable>()

    override fun load() {
        up = createNPD(game.astManager["ButtonUp", Texture::class], 10, 10, 10, 10)
        down = createNPD(game.astManager["ButtonDown", Texture::class], 10, 10, 10, 10)
    }

    override fun show() {
        stage = Stage(ScreenViewport())
        val table: Table = Table()
        stage.addActor(table)
        table.setFillParent(true)
        table.pad(10f)

        val font = game.astManager["UbuntuR-64", BitmapFont::class]
        table.add(createLabel("A la Nave", font, Color.GOLD)).colspan(2).expandX().pad(5f)
        table.row()

        //TODO: Use locales
        val play: TextButton = createButton("Play", font, up, down)
        play.addListener1 { e, a ->
            game.scrManager.change("Game")
        }
        table.add(play).expandX().fill().pad(5f)

        val exit: TextButton = createButton("Exit", font, up, down)
        exit.addListener1 { e, a ->
            Gdx.app.exit()
        }
        table.add(exit).expandX().fill().pad(5f)

        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }

    override fun hide() {
        stage.dispose()
    }
}
