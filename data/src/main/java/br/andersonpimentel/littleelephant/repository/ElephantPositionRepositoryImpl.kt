package br.andersonpimentel.littleelephant.repository

import android.util.Log
import br.andersonpimentel.littleelephant.local.source.ElephantPositionDataSource
import br.andersonpimentel.littleelephant.local.util.toElephantPosition
import br.andersonpimentel.littleelephant.entities.Tile
import br.andersonpimentel.littleelephant.responses.ResultRequired
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ElephantPositionRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val elephantPositionDataSource: ElephantPositionDataSource
):ElephantPositionRepository {

    override fun getLastElephantPosition(): Flow<ResultRequired<Int>> {
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
                    ResultRequired.Empty(e)
                } catch (t: Throwable){
                    ResultRequired.Error(t)
                }
                result
            }

    }

    override suspend fun setLastElephantPosition(tile: Tile.StepTile) {
        withContext(dispatcher) {
            Log.i(
                "***LittleElephant",
                "ElephantPositionRepository | setLastElephantPosition | LastPosition: ${tile.step}"
            )
            elephantPositionDataSource.updateLastElephantPosition(tile.toElephantPosition())
        }
    }
}