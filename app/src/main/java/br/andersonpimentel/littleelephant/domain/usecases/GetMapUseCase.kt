package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.domain.entities.Map
import br.andersonpimentel.littleelephant.domain.entities.Tile
import kotlinx.coroutines.flow.*

class GetMapUseCase(
    private val useCaseGetStepMessages: GetStepMessagesUseCase,
    private val useCaseGetTiles: GetTilesUseCase
    ) {
    suspend operator fun invoke(): Flow<Map> {
        return flowOf(Map(
            tiles = useCaseGetTiles().zip(useCaseGetStepMessages()){ tiles, messages ->
                tiles.apply {
                    if(messages is GetStepMessagesUseCase.ResultMessages.Messages){
                        filterIsInstance<Tile.StepTile>().forEachIndexed { index, tile ->
                            if (messages.list.size > index) {
                                tile.message = messages.list[index].message
                            } else {
                                //TODO Define
                            }
                        }
                    }
                }
            }.first()
        ))
    }
}