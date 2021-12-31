package br.andersonpimentel.littleelephant.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.andersonpimentel.littleelephant.local.model.DateCacheValidate
import kotlinx.coroutines.flow.Flow

@Dao
interface DateCacheValidateDao {
    @Query("SELECT * FROM date_validate")
    fun getLastDateFetchMessages(): Flow<DateCacheValidate>

    @Transaction
    fun updateDateCacheValidate(dateCacheValidate: DateCacheValidate){
        deleteAll()
        insertDateCacheValidate(dateCacheValidate)
    }

    @Insert
    fun insertDateCacheValidate(dateCacheValidate: DateCacheValidate)

    @Query("DELETE FROM date_validate")
    fun deleteAll()
}