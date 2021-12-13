package br.andersonpimentel.littleelephant.domain.entities

import android.graphics.drawable.GradientDrawable

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
        var hasElephant: Boolean = false
    ): Tile()

    enum class Orientation {
        HORIZONTAL, VERTICAL
    }

}
