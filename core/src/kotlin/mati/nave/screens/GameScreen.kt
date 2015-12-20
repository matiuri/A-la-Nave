package mati.nave.screens

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import mati.advancedgdx.screens.Screen
import mati.nave.Game
import mati.nave.land.Path
import mati.nave.land.Wall
import kotlin.properties.Delegates

class GameScreen(game: Game) : Screen(game) {
    private var stage: Stage by Delegates.notNull<Stage>()

    override fun load() {
        if (game is Game) {
            Path.init(game)
            Wall.init(game)
        }
    }

    override fun show() {
        stage = Stage(ScreenViewport())

        val path: Path = Path()
        path.setBounds(50f, 50f, 64f, 64f)
        stage.addActor(path)

        val wall: Wall = Wall()
        wall.setBounds(150f, 150f, 64f, 64f)
        stage.addActor(wall)
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
