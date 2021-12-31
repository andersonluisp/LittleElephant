package br.andersonpimentel.littleelephant.repository

import br.andersonpimentel.littleelephant.entities.Tile
import br.andersonpimentel.littleelephant.responses.ResultRequired
import kotlinx.coroutines.flow.Flow

interface ElephantPositionRepository {

    fun getLastElephantPosition(): Flow<ResultRequired<Int>>

    suspend fun setLastElephantPosition(tile: Tile.StepTile)
}