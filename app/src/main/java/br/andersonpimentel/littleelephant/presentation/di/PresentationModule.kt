package br.andersonpimentel.littleelephant.presentation.di

import br.andersonpimentel.littleelephant.presentation.feature.map.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MainViewModel(
            getMapUseCase = get(),
            setLastElephantPositionUseCase = get()
        )
    }
}