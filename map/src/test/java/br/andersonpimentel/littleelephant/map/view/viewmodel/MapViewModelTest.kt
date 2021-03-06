package br.andersonpimentel.littleelephant.map.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.andersonpimentel.littleelephant.entities.Map
import br.andersonpimentel.littleelephant.entities.Tile
import br.andersonpimentel.littleelephant.usecases.GetMapUseCase
import br.andersonpimentel.littleelephant.usecases.GetMapUseCase.ResultMap
import br.andersonpimentel.littleelephant.usecases.SetLastElephantPositionUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class MapViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getMapUseCase: GetMapUseCase

    @MockK
    private lateinit var setLastElephantPositionUseCase: SetLastElephantPositionUseCase

    @RelaxedMockK
    private lateinit var stateObserver: Observer<ViewState<Map>>
    private lateinit var viewModel: MapViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MapViewModel(
            dispatcher = testDispatcher,
            getMapUseCase = getMapUseCase,
            setLastElephantPositionUseCase = setLastElephantPositionUseCase
        )
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `getMap SHOULD emit ViewState Loading and Success to View`() {
        // Given
        val map = Map(tiles = listOf(Tile.GrassTile(), Tile.RoadHorizontalTile),4)
        coEvery { getMapUseCase() } returns flowOf(ResultMap.Success(map))
        //When
        viewModel.getMap()
        //That
        verifySequence {
            stateObserver.onChanged(ViewState.Loading)
            stateObserver.onChanged(ViewState.Success(map))
        }
    }

    @Test
    fun `setLastElephantPosition method SHOULD be call SetElephantPositionUseCase`() {
        //Given
        val stepTile = Tile.StepTile(
            Tile.Orientation.VERTICAL,
            1,
            false
        )
        coEvery { setLastElephantPositionUseCase(stepTile) } coAnswers {}
        //When
        viewModel.setLastElephantPosition(stepTile)
        //That
        coVerify { setLastElephantPositionUseCase(stepTile) }
    }

    @Test
    fun `getMap SHOULD emit ViewState Loading and Failed WHEN GetMapUseCase failed`() {
        // Given
        coEvery { getMapUseCase() } returns flowOf(ResultMap.Error)
        //When
        viewModel.getMap()
        //That
        verifySequence {
            stateObserver.onChanged(ViewState.Loading)
            stateObserver.onChanged(ViewState.Failed)
        }
    }
}
