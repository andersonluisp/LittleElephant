package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.data.repository.ElephantPositionRepository
import br.andersonpimentel.littleelephant.domain.entities.Tile

class SetLastElephantPositionUseCase(
    private val repository: ElephantPositionRepository
) {

    suspend operator fun invoke(stepTile: Tile.StepTile){
        repository.setLastElephantPosition(stepTile)
    }

}