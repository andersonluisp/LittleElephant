package br.andersonpimentel.littleelephant.remote.util

import br.andersonpimentel.littleelephant.remote.model.FactsResponse
import br.andersonpimentel.littleelephant.entities.Message
import br.andersonpimentel.littleelephant.responses.ResultRemote
import retrofit2.HttpException

fun Throwable.mapRemoteErrors(): ResultRemote.ErrorResponse {
    return when (this) {
        is HttpException -> {
            ResultRemote.ErrorResponse.HttpError(this)
        }
        else -> ResultRemote.ErrorResponse.Unknown(this)
    }
}

fun FactsResponse.toMessageModel(): List<Message> {
    val listUsers = mutableListOf<Message>()
    this.map {
        listUsers.add(
            Message(
                message = it.text
            )
        )
    }
    return listUsers
}