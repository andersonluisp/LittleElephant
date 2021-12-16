package br.andersonpimentel.littleelephant.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetStepTileMessages{
    operator fun invoke(): Flow<List<String>> {
        return flowOf(listOf("1", "2", "3", "4", "5"))
    }
}