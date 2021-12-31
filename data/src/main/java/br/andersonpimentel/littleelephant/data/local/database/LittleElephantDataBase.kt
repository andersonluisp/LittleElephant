package br.andersonpimentel.littleelephant.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.andersonpimentel.littleelephant.data.local.database.dao.DateCacheValidateDao
import br.andersonpimentel.littleelephant.data.local.database.dao.ElephantPositionDao
import br.andersonpimentel.littleelephant.data.local.database.dao.MessagesDao
import br.andersonpimentel.littleelephant.data.local.model.DateCacheValidate
import br.andersonpimentel.littleelephant.data.local.model.ElephantPosition
import br.andersonpimentel.littleelephant.data.local.model.MessagesCache

@Database(
    version = 1,
    entities = [MessagesCache::class, ElephantPosition::class, DateCacheValidate::class]
)
abstract class LittleElephantDataBase : RoomDatabase() {

    abstract fun messagesDao(): MessagesDao
    abstract fun elephantPositionDao(): ElephantPositionDao
    abstract fun dateCacheValidateDao(): DateCacheValidateDao

    companion object {

        fun createDataBase(context: Context): LittleElephantDataBase {
            return Room
                .databaseBuilder(
                    context,
                    LittleElephantDataBase::class.java,
                    "little_elephant_database"
                )
                .build()
        }
    }
}