package br.andersonpimentel.littleelephant.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.andersonpimentel.littleelephant.data.local.model.ElephantPosition
import kotlinx.coroutines.flow.Flow

@Dao
interface ElephantPositionDao {
    @Query("SELECT * FROM elephant_position")
    fun getLastElephantPosition(): Flow<ElephantPosition>

    @Transaction
    fun updateLastElephantPosition(elephantPosition: ElephantPosition){
        deleteAll()
        insertLastElephantPosition(elephantPosition)
    }

    @Insert
    fun insertLastElephantPosition(elephantPosition: ElephantPosition)

    @Query("DELETE FROM elephant_position")
    fun deleteAll()
}