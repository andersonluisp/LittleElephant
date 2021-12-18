package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.domain.entities.Map
import br.andersonpimentel.littleelephant.domain.entities.Tile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip

class GetMapUseCase(
    private val useCaseGetStepMessages: GetStepMessagesUseCase,
    private val useCaseGetTiles: GetTilesUseCase,
    private val useCaseGetLastElephantPosition: GetLastElephantPositionUseCase
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
            }.zip(useCaseGetLastElephantPosition()){ tiles, lastElephantPosition ->
                tiles.apply {
                    if (lastElephantPosition is GetLastElephantPositionUseCase.ResultElephantPosition.ElephantPosition){
                        filterIsInstance<Tile.StepTile>().forEach { tile ->
                            if (tile.step == lastElephantPosition.lastElephantPosition){
                                tile.hasElephant = true
                            }
                        }
                    }
                }
            }.first()
        ))
    }
}