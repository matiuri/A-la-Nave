package mati.nave.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import mati.advancedgdx.screens.Screen
import mati.nave.Game
import mati.nave.input.PlayerInputDesktop
import mati.nave.land.objects.Food
import mati.nave.land.objects.Ship
import mati.nave.land.tiles.Path
import mati.nave.land.tiles.Wall
import mati.nave.mobs.Player
import kotlin.properties.Delegates

class GameScreen(game: Game) : Screen(game) {
    private var stage: Stage by Delegates.notNull<Stage>()

    //TODO: Move this to another class
    private var map: TiledMap by Delegates.notNull<TiledMap>()
    private var renderer: TiledMapRenderer by Delegates.notNull<TiledMapRenderer>()

    override fun load() {
        if (game is Game) {
            Path.init(game)
            Wall.init(game)
            Player.init(game)
        }
        map = TmxMapLoader().load("levels/l0.tmx")
        renderer = OrthogonalTiledMapRenderer(map, 1f)
    }

    override fun show() {
        val cam: OrthographicCamera = OrthographicCamera()
        stage = Stage(FitViewport(800f, 480f, cam))
        renderer.setView(cam)

        map.layers["Objects"].objects.getByType(TextureMapObject::class.java).forEach {
            if (it.properties["name", String::class.java].equals("food")) {
                val food: Food = Food(it.textureRegion)
                food.setBounds(it.x, it.y, 32f, 32f)
                stage.addActor(food)
            } else if (it.properties["name", String::class.java].equals("ship")) {
                val ship: Ship = Ship(it.textureRegion)
                ship.setBounds(it.x, it.y, 32f, 32f)
                stage.addActor(ship)
            }
        }
        val player: Player = Player(map.layers["Objects"].objects.getByType(RectangleMapObject::class.java))
        player.addListener(PlayerInputDesktop(player))
        player.setBounds(32f, 32f, 32f, 32f)
        player.xI = 1
        player.yI = 1
        stage.addActor(player)
        stage.keyboardFocus = player

        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        renderer.render()

        stage.act(delta)
        stage.draw()
    }

    override fun hide() {
        stage.dispose()
    }

    override fun dispose() {
        map.dispose()
    }
}
