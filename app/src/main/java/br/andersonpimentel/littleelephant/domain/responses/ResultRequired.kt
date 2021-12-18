package br.andersonpimentel.littleelephant.domain.responses

sealed class ResultRequired<out T> {
    data class Success<out T>(val result: T): ResultRequired<T>()
    data class Empty(val throwable: Throwable): ResultRequired<Nothing>()
    data class Error(val throwable: Throwable): ResultRequired<Nothing>()
}
