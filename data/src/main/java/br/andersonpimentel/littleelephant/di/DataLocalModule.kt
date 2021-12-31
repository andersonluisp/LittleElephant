package br.andersonpimentel.littleelephant.di

import br.andersonpimentel.littleelephant.local.database.LittleElephantDataBase
import br.andersonpimentel.littleelephant.local.source.DateCacheValidateDataSource
import br.andersonpimentel.littleelephant.local.source.ElephantPositionDataSource
import br.andersonpimentel.littleelephant.local.source.MessagesCacheDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single { LittleElephantDataBase.createDataBase(androidContext())}
    factory { MessagesCacheDataSource(dataBase = get()) }
    factory { DateCacheValidateDataSource(database = get()) }
    factory { ElephantPositionDataSource(dataBase = get()) }
}