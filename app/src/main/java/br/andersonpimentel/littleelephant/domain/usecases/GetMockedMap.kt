package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.domain.entities.Tile

interface GetMockedMapInterface {
    operator fun invoke(): List<Tile>
}

class GetMockedMap: GetMockedMapInterface {
    override fun invoke(): List<Tile> {
        val tiles = listOf<Tile>(
            Tile.TreeTile, Tile.GrassTile, Tile.RoadVerticalTile, Tile.GrassTile,
            Tile.GrassTile, Tile.GrassTile, Tile.StepTile(Tile.Orientation.VERTICAL, 1), Tile.GrassTile,
            Tile.GrassTile, Tile.TopTurnLeftTile, Tile.BottomTurnLeftTile, Tile.GrassTile,
            Tile.GrassTile, Tile.StepTile(Tile.Orientation.VERTICAL, 2), Tile.GrassTile, Tile.TreeTile,
            Tile.GrassTile, Tile.BottomTurnRightTile, Tile.RoadHorizontalTile, Tile.TopTurnRightTile,
            Tile.GrassTile, Tile.GrassTile, Tile.TreeTile, Tile.RoadVerticalTile,
            Tile.GrassTile, Tile.TopTurnLeftTile, Tile.StepTile(Tile.Orientation.HORIZONTAL, 3), Tile.BottomTurnLeftTile,
            Tile.TreeTile, Tile.RoadVerticalTile, Tile.GrassTile, Tile.GrassTile,
            Tile.GrassTile, Tile.StepTile(Tile.Orientation.VERTICAL, 4), Tile.GrassTile, Tile.GrassTile
        )
        return tiles
    }
}