package br.andersonpimentel.littleelephant.data.remote.repository

import br.andersonpimentel.littleelephant.data.remote.source.RemoteDataSource
import br.andersonpimentel.littleelephant.data.remote.util.toMessageModel
import br.andersonpimentel.littleelephant.domain.entities.Message
import br.andersonpimentel.littleelephant.domain.responses.ResultRemote
import br.andersonpimentel.littleelephant.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MessagesRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getFactsRemote(): Flow<ResultRequired<List<Message>>> {
        val resultRemote = remoteDataSource.fetchMessages()
        return flowOf(when(resultRemote){
            is ResultRemote.Success -> {
                ResultRequired.Success(result = resultRemote.response.toMessageModel())
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(resultRemote.throwable)
            }
        })
    }
}