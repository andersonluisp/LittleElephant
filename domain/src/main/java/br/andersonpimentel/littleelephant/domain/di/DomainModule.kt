package br.andersonpimentel.littleelephant.domain.di

import br.andersonpimentel.littleelephant.domain.usecases.*
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