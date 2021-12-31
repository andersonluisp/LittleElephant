package br.andersonpimentel.littleelephant.usecases

import br.andersonpimentel.littleelephant.entities.Message
import br.andersonpimentel.littleelephant.repository.MessagesRepository
import br.andersonpimentel.littleelephant.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetStepMessagesUseCase(
    private val repository: MessagesRepository
) {
    suspend operator fun invoke(): Flow<ResultMessages> {
        return repository.getMessages()
            .map {
                when (it) {
                    is ResultRequired.Success -> {
                        when {
                            it.result.isEmpty() -> ResultMessages.NoMessages
                            else -> ResultMessages.Messages(it.result)
                        }
                    }
                    is ResultRequired.Error -> {
                        ResultMessages.Error
                    }
                    is ResultRequired.Empty -> ResultMessages.NoMessages
                }
            }
    }

    sealed class ResultMessages {
        data class Messages(val list: List<Message>) : ResultMessages()
        object NoMessages : ResultMessages()
        object Error : ResultMessages()
    }
}