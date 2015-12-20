package mati.nave.land.tiles

import mati.nave.land.Land

interface Tile : Land {
    fun isCollisionable(): Boolean

    fun doesHurt(): Boolean
}
