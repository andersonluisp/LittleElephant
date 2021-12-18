package br.andersonpimentel.littleelephant.data.repository

import android.util.Log
import br.andersonpimentel.littleelephant.data.local.source.ElephantPositionDataSource
import br.andersonpimentel.littleelephant.data.local.util.toElephantPosition
import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.domain.responses.ResultRequired
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ElephantPositionRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val elephantPositionDataSource: ElephantPositionDataSource
) {

    fun getLastElephantPosition(): Flow<ResultRequired<Int>> {
        return elephantPositionDataSource.getLastElephantPosition()
            .map { lastElephantStep ->
                val result = try {
                    Log.i(
                        "***LittleElephant",
                        "ElephantPositionRepository | getLastElephantPosition | LastPosition: ${lastElephantStep.lastElephantPosition}"
                    )
                    ResultRequired.Success(lastElephantStep.lastElephantPosition)
                } catch (e: NullPointerException){
                    Log.i(
                        "***LittleElephant",
                        "ElephantPositionRepository | getLastElephantPosition | The elephant has not a position yet"
                    )
                    ResultRequired.Error(e)
                } catch (t: Throwable){
                    ResultRequired.Error(t)
                }
                result
            }

    }

    suspend fun setLastElephantPosition(tile: Tile.StepTile) {
        withContext(dispatcher) {
            Log.i(
                "***LittleElephant",
                "ElephantPositionRepository | setLastElephantPosition | LastPosition: ${tile.step}"
            )
            elephantPositionDataSource.updateLastElephantPosition(tile.toElephantPosition())
        }
    }

}