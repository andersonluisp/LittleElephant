package br.andersonpimentel.littleelephant.presentation.feature.viewmodel

sealed class ViewState<out T> {
    object Loading: ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    object Failed : ViewState<Nothing>()
}