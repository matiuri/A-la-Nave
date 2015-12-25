package mati.nave.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import mati.advancedgdx.screens.Screen
import mati.advancedgdx.utils.addListener1
import mati.advancedgdx.utils.createButton
import mati.advancedgdx.utils.createLabel
import mati.advancedgdx.utils.createNPD
import mati.nave.Game
import mati.nave.gui.PlanetButton
import mati.nave.input.PlanetButtonInput
import kotlin.properties.Delegates

class LevelSelectScreen(game: Game) : Screen(game) {
    private var stage: Stage by Delegates.notNull<Stage>()
    private var up: NinePatchDrawable by Delegates.notNull<NinePatchDrawable>()
    private var down: NinePatchDrawable by Delegates.notNull<NinePatchDrawable>()

    override fun load() {
        up = createNPD(game.astManager["ButtonUp", Texture::class], 10, 10, 10, 10)
        down = createNPD(game.astManager["ButtonDown", Texture::class], 10, 10, 10, 10)
    }

    override fun show() {
        stage = Stage(ScreenViewport())
        val font = game.astManager["UbuntuR-64", BitmapFont::class]
        val label: Label = createLabel("Level Select", font, Color.GOLD)
        label.setPosition(stage.width / 2 - label.width / 2, stage.height - label.height - 10)
        stage.addActor(label)

        val levels: Int = 3
        for (i in 0..(levels - 1)) {
            val planet: PlanetButton = PlanetButton(game as Game, i, Color(MathUtils.random(), MathUtils.random(),
                    MathUtils.random(), 1f)) {
                (game.scrManager["Game"] as GameScreen).levelPath = "levels/l$i.tmx"
                game.scrManager.change("Game")
            }
            planet.setBounds(256f * i, stage.height / 2 - 128, 256f, 256f)
            planet.addListener(PlanetButtonInput(planet))
            stage.addActor(planet)
        }
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
