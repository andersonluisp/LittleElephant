package br.andersonpimentel.littleelephant.repository

import br.andersonpimentel.littleelephant.entities.Message
import br.andersonpimentel.littleelephant.responses.ResultRequired
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
    suspend fun getMessages(): Flow<ResultRequired<List<Message>>>
}