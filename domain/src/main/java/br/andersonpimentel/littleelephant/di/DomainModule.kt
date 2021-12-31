package br.andersonpimentel.littleelephant.di

import br.andersonpimentel.littleelephant.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetLastElephantPositionUseCase(repository = get()) }

    factory { GetStepMessagesUseCase(repository = get()) }

    factory { GetTilesUseCase() }

    factory { SetLastElephantPositionUseCase(repository = get()) }

    factory {
        GetMapUseCase(
            useCaseGetLastElephantPosition = get(),
            useCaseGetStepMessages = get(),
            useCaseGetTiles = get()
        )
    }
}