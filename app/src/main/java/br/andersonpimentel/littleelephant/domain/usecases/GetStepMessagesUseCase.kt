package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.data.remote.repository.MessagesRepository
import br.andersonpimentel.littleelephant.domain.entities.Message
import br.andersonpimentel.littleelephant.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetStepMessagesUseCase(
    private val repository: MessagesRepository
){
    suspend operator fun invoke(): Flow<ResultMessages> {
        return repository.getFactsRemote()
            .map{
                when(it) {
                    is ResultRequired.Success -> {
                        when{
                            it.result.isEmpty() -> ResultMessages.NoMessages
                            else -> ResultMessages.Messages(it.result)
                        }
                    }
                    is ResultRequired.Error -> {
                        ResultMessages.Error
                    }
                }
            }
    }

    sealed class ResultMessages {
        data class Messages(val list: List<Message>) : ResultMessages()
        object NoMessages : ResultMessages()
        object Error : ResultMessages()
    }
}