package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.domain.entities.Tile
import kotlinx.coroutines.flow.*

class GetMapUseCase(private val useCaseGetStepTileMessages: GetStepTileMessages) {
    operator fun invoke(): Flow<List<Tile>> {
        val stepTileMessages = useCaseGetStepTileMessages()
        val tileList = flowOf(listOf(
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

        return tileList.zip(stepTileMessages){ tiles, messages ->
            tiles.apply {
                filterIsInstance<Tile.StepTile>().forEachIndexed { index, tile ->
                    if (messages.size > index) {
                        tile.message = messages[index]
                    } else {
                        //TODO Define
                    }
                }
            }
        }
    }
}