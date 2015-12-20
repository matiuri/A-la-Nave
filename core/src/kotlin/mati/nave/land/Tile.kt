package mati.nave.land

interface Tile {
    fun isCollisionable(): Boolean

    fun doesHurt(): Boolean
}
