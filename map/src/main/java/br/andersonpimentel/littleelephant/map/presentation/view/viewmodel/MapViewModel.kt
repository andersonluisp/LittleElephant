package br.andersonpimentel.littleelephant.map.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.andersonpimentel.littleelephant.domain.entities.Map
import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.domain.usecases.GetMapUseCase
import br.andersonpimentel.littleelephant.domain.usecases.GetMapUseCase.ResultMap
import br.andersonpimentel.littleelephant.domain.usecases.SetLastElephantPositionUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MapViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getMapUseCase: GetMapUseCase,
    private val setLastElephantPositionUseCase: SetLastElephantPositionUseCase
) : ViewModel() {

    private val _state = MutableLiveData<ViewState<Map>>().apply {
            value = ViewState.Loading
    }
    var state: LiveData<ViewState<Map>> = _state

    fun getMap() {
        viewModelScope.launch(dispatcher) {
            getMapUseCase()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    when(result){
                        is ResultMap.Success -> _state.postValue(ViewState.Success(result.map))
                        is ResultMap.MapIsEmpty -> _state.postValue(ViewState.Failed)
                        is ResultMap.Error -> _state.postValue(ViewState.Failed)
                    }
                }
        }
    }

    fun setLastElephantPosition(tile: Tile.StepTile) {
        viewModelScope.launch(dispatcher) {
            setLastElephantPositionUseCase(tile)
        }
    }
}