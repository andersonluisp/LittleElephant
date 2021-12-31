package br.andersonpimentel.littleelephant.data.di

import br.andersonpimentel.littleelephant.data.remote.util.SuccessRemoteFetch
import br.andersonpimentel.littleelephant.data.repository.ElephantPositionRepositoryImpl
import br.andersonpimentel.littleelephant.data.repository.MessagesRepositoryImpl
import br.andersonpimentel.littleelephant.domain.repository.ElephantPositionRepository
import br.andersonpimentel.littleelephant.domain.repository.MessagesRepository
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