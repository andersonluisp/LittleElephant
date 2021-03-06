package br.andersonpimentel.littleelephant.usecases

import br.andersonpimentel.littleelephant.entities.Map
import br.andersonpimentel.littleelephant.entities.Tile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip

class GetMapUseCase(
    private val useCaseGetStepMessages: GetStepMessagesUseCase,
    private val useCaseGetTiles: GetTilesUseCase,
    private val useCaseGetLastElephantPosition: GetLastElephantPositionUseCase
    ) {
    suspend operator fun invoke(): Flow<ResultMap> {

        return  try {
            flowOf(if(useCaseGetTiles().first().isNullOrEmpty()){
                ResultMap.MapIsEmpty
            } else{
                ResultMap.Success(map = Map(
                    tiles = useCaseGetTiles().zip(useCaseGetStepMessages()){ tiles, messages ->
                        tiles.apply {
                            if(messages is GetStepMessagesUseCase.ResultMessages.Messages){
                                filterIsInstance<Tile.StepTile>().forEachIndexed { index, tile ->
                                    if (messages.list.size > index) {
                                        tile.message = messages.list[index].message
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
                , spanCount = 4
                ))
            })
        } catch (t: Throwable){
            flowOf(ResultMap.Error)
        }
    }

    sealed class ResultMap {
        data class Success(val map: Map) : ResultMap()
        object MapIsEmpty :  ResultMap()
        object Error : ResultMap()
    }
}