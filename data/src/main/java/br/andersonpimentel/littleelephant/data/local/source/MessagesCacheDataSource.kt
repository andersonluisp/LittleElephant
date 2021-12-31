package br.andersonpimentel.littleelephant.data.local.source

import android.util.Log
import br.andersonpimentel.littleelephant.data.local.database.LittleElephantDataBase
import br.andersonpimentel.littleelephant.data.local.model.MessagesCache
import kotlinx.coroutines.flow.Flow

class MessagesCacheDataSource(
    private val dataBase: LittleElephantDataBase,
) {
    fun getMessages(): Flow<List<MessagesCache>>{
        Log.i("***LittleElephant", "MessagesCacheDataSource | getMessages | GetMessages from Room")
        return dataBase.messagesDao().getMessages()
    }

    fun updateMessages(cacheList: List<MessagesCache>){
        dataBase.messagesDao().updateMessages(cacheList)
        Log.i("***LittleElephant", "MessagesCacheDataSource | updateMessages | Update the messages on Room")
    }
}