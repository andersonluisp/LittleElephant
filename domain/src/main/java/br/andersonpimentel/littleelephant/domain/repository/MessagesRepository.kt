package br.andersonpimentel.littleelephant.domain.repository

import br.andersonpimentel.littleelephant.domain.entities.Message
import br.andersonpimentel.littleelephant.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
    suspend fun getMessages(): Flow<ResultRequired<List<Message>>>
}