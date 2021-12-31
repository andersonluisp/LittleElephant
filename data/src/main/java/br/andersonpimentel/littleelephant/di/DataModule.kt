package br.andersonpimentel.littleelephant.di

import br.andersonpimentel.littleelephant.remote.util.SuccessRemoteFetch
import br.andersonpimentel.littleelephant.repository.ElephantPositionRepositoryImpl
import br.andersonpimentel.littleelephant.repository.MessagesRepositoryImpl
import br.andersonpimentel.littleelephant.repository.ElephantPositionRepository
import br.andersonpimentel.littleelephant.repository.MessagesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory { SuccessRemoteFetch(context = androidContext()) }

    factory<MessagesRepository> {
        MessagesRepositoryImpl(
            messagesCacheDataSource = get(),
            dateCacheValidateDataSource = get(),
            remoteDataSource = get(),
            onSuccessRemoteFetch = get()
        )
    }

    factory<ElephantPositionRepository> {
        ElephantPositionRepositoryImpl(
            elephantPositionDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, localDataModule, repositoryModule)