package br.andersonpimentel.littleelephant.entities

sealed class Tile {
    object BottomToRightTile : Tile()
    object BottomToLeftTile : Tile()
    object TopToRightTile : Tile()
    object TopToLeftTile : Tile()
    object RoadHorizontalTile : Tile()
    object RoadVerticalTile : Tile()
    data class GrassTile(
        val hasTree: Boolean = false
    ): Tile()

    data class StepTile(
        val orientation: Orientation,
        val step: Int,
        var hasElephant: Boolean = false,
        var message: String? = null
    ): Tile()

    enum class Orientation {
        HORIZONTAL, VERTICAL
    }

}
