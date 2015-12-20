package mati.nave.land

interface Tile {
    fun isCollisionable(): Boolean

    fun doesHurt(): Boolean

    fun setBounds(x: Float, y: Float, width: Float, height: Float)
}
