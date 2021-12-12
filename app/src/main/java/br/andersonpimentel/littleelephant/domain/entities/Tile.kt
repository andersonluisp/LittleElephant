package br.andersonpimentel.littleelephant.domain.entities

import android.graphics.drawable.GradientDrawable

sealed class Tile {
    object BottomTurnRightTile : Tile()
    object BottomTurnLeftTile : Tile()
    object TopTurnRightTile : Tile()
    object TopTurnLeftTile : Tile()
    object RoadHorizontalTile : Tile()
    object RoadVerticalTile : Tile()
    object GrassTile : Tile()
    object TreeTile: Tile()

    data class StepTile(
        val orientation: Orientation,
        val step: Int,
        var hasElephant: Boolean = false
    ): Tile()

    enum class Orientation {
        HORIZONTAL, VERTICAL
    }

}
