package br.andersonpimentel.littleelephant.presentation.feature.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.andersonpimentel.littleelephant.domain.entities.Map
import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.domain.usecases.GetMapUseCase
import br.andersonpimentel.littleelephant.domain.usecases.SetLastElephantPositionUseCase
import br.andersonpimentel.littleelephant.presentation.feature.viewmodel.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getMapUseCase: GetMapUseCase,
    private val setLastElephantPositionUseCase: SetLastElephantPositionUseCase
) : ViewModel() {

    private val _state = MutableLiveData<ViewState<Map>>().apply {
        value = ViewState.Loading
    }
    var state: LiveData<ViewState<Map>> = _state

    init {
        getMap()
    }

    private fun getMap() {
        viewModelScope.launch(dispatcher) {
            try {
                getMapUseCase().flowOn(Dispatchers.IO)
                    .collect { map ->
                        _state.postValue(ViewState.Success(map))
                    }
            }catch (e: Throwable){
                _state.postValue(ViewState.Failed)
            }
        }
    }

    fun setLastElephantPosition(tile: Tile.StepTile) {
        viewModelScope.launch(dispatcher) {
            setLastElephantPositionUseCase(tile)
        }
    }

}