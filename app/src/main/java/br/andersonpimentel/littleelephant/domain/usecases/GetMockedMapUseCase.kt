package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.domain.entities.Tile

interface GetMockedMapUseCaseInterface {
    fun execute(): List<Tile>
}

class GetMockedMapUseCase: GetMockedMapUseCaseInterface {
    override fun execute(): List<Tile> {
        return listOf(
            Tile.GrassTile(hasTree = true), Tile.GrassTile(), Tile.RoadVerticalTile, Tile.GrassTile(),
            Tile.GrassTile(), Tile.GrassTile(), Tile.StepTile(Tile.Orientation.VERTICAL, 1), Tile.GrassTile(),
            Tile.GrassTile(), Tile.TopToLeftTile, Tile.BottomToLeftTile, Tile.GrassTile(),
            Tile.GrassTile(), Tile.StepTile(Tile.Orientation.VERTICAL, 2), Tile.GrassTile(), Tile.GrassTile(hasTree = true),
            Tile.GrassTile(), Tile.BottomToRightTile, Tile.RoadHorizontalTile, Tile.TopToRightTile,
            Tile.GrassTile(), Tile.GrassTile(), Tile.GrassTile(hasTree = true), Tile.RoadVerticalTile,
            Tile.GrassTile(), Tile.TopToLeftTile, Tile.StepTile(Tile.Orientation.HORIZONTAL, 3), Tile.BottomToLeftTile,
            Tile.GrassTile(hasTree = true), Tile.RoadVerticalTile, Tile.GrassTile(), Tile.GrassTile(),
            Tile.GrassTile(), Tile.StepTile(Tile.Orientation.VERTICAL, 4), Tile.GrassTile(), Tile.GrassTile()
        )
    }
}