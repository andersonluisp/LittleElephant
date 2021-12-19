package br.andersonpimentel.littleelephant.data.di

import br.andersonpimentel.littleelephant.data.remote.util.SuccessRemoteFetch
import br.andersonpimentel.littleelephant.data.repository.ElephantPositionRepository
import br.andersonpimentel.littleelephant.data.repository.MessagesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory { SuccessRemoteFetch(context = androidContext()) }

    factory {
        MessagesRepository(
            messagesCacheDataSource = get(),
            dateCacheValidateDataSource = get(),
            remoteDataSource = get(),
            onSuccessRemoteFetch = get()
        )
    }

    factory {
        ElephantPositionRepository(
            elephantPositionDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, localDataModule, repositoryModule)