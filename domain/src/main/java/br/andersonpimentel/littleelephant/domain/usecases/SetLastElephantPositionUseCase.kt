package br.andersonpimentel.littleelephant.domain.usecases

import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.domain.repository.ElephantPositionRepository

class SetLastElephantPositionUseCase(
    private val repository: ElephantPositionRepository
) {

    suspend operator fun invoke(stepTile: Tile.StepTile){
        repository.setLastElephantPosition(stepTile)
    }

}