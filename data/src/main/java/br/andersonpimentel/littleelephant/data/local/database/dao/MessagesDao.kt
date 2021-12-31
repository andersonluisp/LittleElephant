package br.andersonpimentel.littleelephant.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.andersonpimentel.littleelephant.data.local.model.MessagesCache
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {

    @Query("SELECT * FROM messages")
    fun getMessages(): Flow<List<MessagesCache>>

    @Transaction
    fun updateMessages(messages: List<MessagesCache>){
        deleteAll()
        insertAll(messages)
    }

    @Insert
    fun insertAll(messages: List<MessagesCache>)

    @Query("DELETE FROM messages")
    fun deleteAll()
}