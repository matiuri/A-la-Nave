package mati.nave.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.objects.TextureMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import mati.advancedgdx.screens.Screen
import mati.nave.Game
import mati.nave.gui.PlayerEnergyDisplay
import mati.nave.input.PlayerInputDesktop
import mati.nave.mobs.Enemy
import mati.nave.mobs.Player
import mati.nave.objects.Food
import mati.nave.objects.Key
import mati.nave.objects.Lock
import mati.nave.objects.Ship
import java.util.*
import kotlin.properties.Delegates

class GameScreen(game: Game) : Screen(game) {
    var enemies: Array<Enemy>? = null
    var levelPath: String = "levels/l0.tmx"

    private var stage: Stage by Delegates.notNull<Stage>()

    //TODO: Move this to another class
    private var map: TiledMap by Delegates.notNull<TiledMap>()
    private var renderer: TiledMapRenderer by Delegates.notNull<TiledMapRenderer>()
    private var player: Player by Delegates.notNull<Player>()

    override fun load() {
        if (game is Game) {
            Player.init(game)
            PlayerEnergyDisplay.init(game)
            Lock.init(game)
            Key.init(game)
            Enemy.init(game)
        }
    }

    override fun show() {
        map = TmxMapLoader().load(levelPath)
        renderer = OrthogonalTiledMapRenderer(map, 1f)

        val cam: OrthographicCamera = OrthographicCamera()
        stage = Stage(FitViewport(800f, 480f, cam))
        renderer.setView(cam)

        val ships: Array<Rectangle?> = arrayOfNulls(9) // It's 9 because the ship is 3x3
        var index: Int = 0
        val pairs: MutableMap<TextureMapObject, Actor> = HashMap()
        map.layers["Objects"].objects.getByType(TextureMapObject::class.java).forEach {
            if (it.properties["name", String::class.java].equals("food")) {
                val food: Food = Food(game as Game)
                food.setBounds(it.x, it.y, 32f, 32f)
                stage.addActor(food)
                pairs.put(it, food)
            } else if (it.properties["name", String::class.java].equals("ship")) {
                val ship: Ship = Ship(it.textureRegion)
                ship.setBounds(it.x, it.y, 32f, 32f)
                ships[index++] = ship.bb
                stage.addActor(ship)
            } else if (it.properties["name", String::class.java].equals("lock")) {
                val lock: Lock = Lock(it.properties["pair", String::class.java].toInt())
                lock.setBounds(it.x, it.y, 32f, 32f)
                stage.addActor(lock)
                pairs.put(it, lock)
            } else if (it.properties["name", String::class.java].equals("key")) {
                val key: Key = Key(it.properties["pair", String::class.java].toInt())
                key.setBounds(it.x, it.y, 32f, 32f)
                stage.addActor(key)
                pairs.put(it, key)
            }
        }

        val shipsNN: Array<Rectangle> = Array(ships.size) {
            ships[it]!!
        }
        player = Player(map.layers["Objects"].objects.getByType(RectangleMapObject::class.java), shipsNN, map, pairs)
        player.addListener(PlayerInputDesktop(player))
        player.setBounds(32f, 32f, 32f, 32f)
        player.xI = 1
        player.yI = 1
        stage.addActor(player)
        stage.keyboardFocus = player

        if (enemies != null) {
            Enemy.player = player
            player.enemies = enemies
            for (e in enemies!!) {
                stage.addActor(e)
            }
        }

        val energies: PlayerEnergyDisplay = PlayerEnergyDisplay(player)
        energies.setPosition(10f, stage.height - 30)
        player.display = energies
        stage.addActor(energies)

        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        renderer.render()

        stage.act(delta)
        stage.draw()
    }

    override fun hide() {
        stage.dispose()
        map.dispose()
        player.enemies = null
        enemies = null
    }
}
