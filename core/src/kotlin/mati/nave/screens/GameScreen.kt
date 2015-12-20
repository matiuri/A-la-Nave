package mati.nave.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import mati.advancedgdx.screens.Screen
import mati.nave.Game
import mati.nave.input.PlayerInputDesktop
import mati.nave.land.Land
import mati.nave.land.objects.Ship
import mati.nave.land.tiles.Path
import mati.nave.land.tiles.Wall
import mati.nave.levels.LevelGenerator
import mati.nave.levels.LevelMaps
import mati.nave.mobs.Player
import kotlin.properties.Delegates

class GameScreen(game: Game) : Screen(game) {
    private var stage: Stage by Delegates.notNull<Stage>()

    override fun load() {
        if (game is Game) {
            Path.init(game)
            Wall.init(game)
            Ship.init(game)
            Player.init(game)
        }
    }

    override fun show() {
        stage = Stage(ExtendViewport(800f, 480f))
        val tiles: Array<Array<out Land>> = LevelGenerator.generate(LevelMaps.t0)
        tiles.forEach {
            it.forEach {
                if (it is Actor)
                    stage.addActor(it)
            }
        }
        val objects: Array<Array<out Land>> = LevelGenerator.generate(LevelMaps.o0)
        objects.forEach {
            it.forEach {
                if (it is Actor)
                    stage.addActor(it)
            }
        }

        val player: Player = Player(tiles, objects)
        player.addListener(PlayerInputDesktop(player))
        player.setBounds(25f, 25f, 25f, 25f)
        player.xI = 1
        player.yI = 1
        stage.addActor(player)
        stage.keyboardFocus = player

        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
