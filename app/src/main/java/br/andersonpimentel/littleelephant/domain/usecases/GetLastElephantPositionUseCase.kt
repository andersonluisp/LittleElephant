package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.data.repository.ElephantPositionRepository
import br.andersonpimentel.littleelephant.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLastElephantPositionUseCase(
    private val repository: ElephantPositionRepository
) {
    operator fun invoke(): Flow<ResultElephantPosition> {
        return repository.getLastElephantPosition()
            .map {
                when (it) {
                    is ResultRequired.Success -> {
                        ResultElephantPosition.ElephantPosition(it.result)
                    }
                    is ResultRequired.Empty -> {
                        ResultElephantPosition.NoElephantPosition
                    }
                    is ResultRequired.Error -> {
                        ResultElephantPosition.Error
                    }
                }
            }
    }

    sealed class ResultElephantPosition {
        data class ElephantPosition(val lastElephantPosition: Int) : ResultElephantPosition()
        object NoElephantPosition : ResultElephantPosition()
        object Error : ResultElephantPosition()
    }
}