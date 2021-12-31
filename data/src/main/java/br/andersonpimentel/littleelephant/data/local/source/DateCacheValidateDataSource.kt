package br.andersonpimentel.littleelephant.data.local.source

import android.util.Log
import br.andersonpimentel.littleelephant.data.local.database.LittleElephantDataBase
import br.andersonpimentel.littleelephant.data.local.model.DateCacheValidate
import kotlinx.coroutines.flow.Flow

class DateCacheValidateDataSource(
    private val database: LittleElephantDataBase
) {
    fun getLastDateFetchMessages(): Flow<DateCacheValidate>{
        Log.i("***LittleElephant", "DateCacheValidateDataSource | getLastDateFetchMessages | Get the cache date")
        return database.dateCacheValidateDao().getLastDateFetchMessages()
    }

    fun updateDateCacheValidate(dateCacheValidate: DateCacheValidate){
        database.dateCacheValidateDao().updateDateCacheValidate(dateCacheValidate)
        Log.i("***LittleElephant", "DateCacheValidateDataSource | updateDateCacheValidate | Updated the cache date")
    }
}