package br.andersonpimentel.littleelephant.map.di

import br.andersonpimentel.littleelephant.map.view.viewmodel.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MapViewModel(
            getMapUseCase = get(),
            setLastElephantPositionUseCase = get()
        )
    }
}