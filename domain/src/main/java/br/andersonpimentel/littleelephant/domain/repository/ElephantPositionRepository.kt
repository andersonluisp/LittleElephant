package br.andersonpimentel.littleelephant.domain.repository

import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow

interface ElephantPositionRepository {

    fun getLastElephantPosition(): Flow<ResultRequired<Int>>

    suspend fun setLastElephantPosition(tile: Tile.StepTile)
}