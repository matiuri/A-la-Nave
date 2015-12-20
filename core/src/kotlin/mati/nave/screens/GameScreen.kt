package mati.nave.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import mati.advancedgdx.screens.Screen
import mati.nave.Game
import mati.nave.land.Path
import mati.nave.land.Tile
import mati.nave.land.Wall
import mati.nave.levels.LevelGenerator
import mati.nave.levels.LevelMaps
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
        val tiles: Array<Array<Tile>> = LevelGenerator.generate(LevelMaps.l1)
        tiles.forEach {
            it.forEach {
                if (it is Actor)
                    stage.addActor(it)
            }
        }
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
