package br.andersonpimentel.littleelephant.usecases

import br.andersonpimentel.littleelephant.entities.Tile
import br.andersonpimentel.littleelephant.repository.ElephantPositionRepository

class SetLastElephantPositionUseCase(
    private val repository: ElephantPositionRepository
) {

    suspend operator fun invoke(stepTile: Tile.StepTile){
        repository.setLastElephantPosition(stepTile)
    }

}