package br.andersonpimentel.littleelephant.data.remote.util

import br.andersonpimentel.littleelephant.data.remote.model.FactsResponse
import br.andersonpimentel.littleelephant.domain.entities.Message
import br.andersonpimentel.littleelephant.domain.responses.ResultRemote
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