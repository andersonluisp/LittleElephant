package br.andersonpimentel.littleelephant.data.di

import br.andersonpimentel.littleelephant.data.repository.ElephantPositionRepository
import br.andersonpimentel.littleelephant.data.repository.MessagesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        MessagesRepository(
            messagesCacheDataSource = get(),
            dateCacheValidateDataSource = get(),
            remoteDataSource = get()
        )
    }

    factory {
        ElephantPositionRepository(
            elephantPositionDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, localDataModule, repositoryModule)