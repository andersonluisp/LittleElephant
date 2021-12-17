package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.domain.entities.Tile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetTilesUseCase {
    operator fun invoke(): Flow<List<Tile>> {
        return flowOf(listOf(
            Tile.GrassTile(hasTree = true), Tile.GrassTile(), Tile.RoadVerticalTile, Tile.GrassTile(),
            Tile.GrassTile(), Tile.GrassTile(), Tile.StepTile(Tile.Orientation.VERTICAL, 1), Tile.GrassTile(),
            Tile.GrassTile(), Tile.TopToLeftTile, Tile.BottomToLeftTile, Tile.GrassTile(),
            Tile.GrassTile(), Tile.StepTile(Tile.Orientation.VERTICAL, 2), Tile.GrassTile(), Tile.GrassTile(hasTree = true),
            Tile.GrassTile(), Tile.BottomToRightTile, Tile.RoadHorizontalTile, Tile.TopToRightTile,
            Tile.GrassTile(), Tile.GrassTile(), Tile.GrassTile(hasTree = true), Tile.RoadVerticalTile,
            Tile.GrassTile(), Tile.TopToLeftTile, Tile.StepTile(Tile.Orientation.HORIZONTAL, 3), Tile.BottomToLeftTile,
            Tile.GrassTile(hasTree = true), Tile.RoadVerticalTile, Tile.GrassTile(), Tile.GrassTile(),
            Tile.StepTile(Tile.Orientation.HORIZONTAL, 4), Tile.BottomToLeftTile, Tile.GrassTile(), Tile.GrassTile()
        ))
    }
}