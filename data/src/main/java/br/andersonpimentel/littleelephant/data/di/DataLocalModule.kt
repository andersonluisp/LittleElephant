package br.andersonpimentel.littleelephant.data.di

import br.andersonpimentel.littleelephant.data.local.database.LittleElephantDataBase
import br.andersonpimentel.littleelephant.data.local.source.DateCacheValidateDataSource
import br.andersonpimentel.littleelephant.data.local.source.ElephantPositionDataSource
import br.andersonpimentel.littleelephant.data.local.source.MessagesCacheDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single { LittleElephantDataBase.createDataBase(androidContext())}
    factory { MessagesCacheDataSource(dataBase = get()) }
    factory { DateCacheValidateDataSource(database = get()) }
    factory { ElephantPositionDataSource(dataBase = get()) }
}