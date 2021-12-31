package br.andersonpimentel.littleelephant.local.source

import android.util.Log
import br.andersonpimentel.littleelephant.local.database.LittleElephantDataBase
import br.andersonpimentel.littleelephant.local.model.ElephantPosition
import kotlinx.coroutines.flow.Flow

class ElephantPositionDataSource(
    private val dataBase: LittleElephantDataBase
) {
    fun getLastElephantPosition(): Flow<ElephantPosition> {
        Log.i("***LittleElephant", "ElephantPositionDataSource | getLastElephantPosition | Get the Last Elephant Position")
        return dataBase.elephantPositionDao().getLastElephantPosition()
    }

    fun updateLastElephantPosition(elephantPosition: ElephantPosition) {
        dataBase.elephantPositionDao().updateLastElephantPosition(elephantPosition)
        Log.i("***LittleElephant", "ElephantPositionDataSource | updateLastElephantPosition | Updated Last Elephant Position")
    }
}