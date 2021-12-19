package br.andersonpimentel.littleelephant.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.andersonpimentel.littleelephant.domain.entities.Map
import br.andersonpimentel.littleelephant.domain.entities.Message
import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.domain.usecases.GetLastElephantPositionUseCase
import br.andersonpimentel.littleelephant.domain.usecases.GetLastElephantPositionUseCase.ResultElephantPosition
import br.andersonpimentel.littleelephant.domain.usecases.GetMapUseCase
import br.andersonpimentel.littleelephant.domain.usecases.GetMapUseCase.ResultMap
import br.andersonpimentel.littleelephant.domain.usecases.GetStepMessagesUseCase
import br.andersonpimentel.littleelephant.domain.usecases.GetStepMessagesUseCase.ResultMessages
import br.andersonpimentel.littleelephant.domain.usecases.GetTilesUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class GetMapUseCaseTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getStepMessagesUseCase: GetStepMessagesUseCase

    @MockK
    private lateinit var getTilesUseCase: GetTilesUseCase

    @MockK
    private lateinit var getLastElephantPositionUseCase: GetLastElephantPositionUseCase
    private lateinit var getMapUseCase: GetMapUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getMapUseCase =
            GetMapUseCase(getStepMessagesUseCase, getTilesUseCase, getLastElephantPositionUseCase)
    }

    @Test
    fun `GetMapUseCase SHOULD emit the map WHEN receive the Tiles`() =
        runBlockingTest {
            // Given
            val messages = listOf<Message>()
            val lastElephantPosition = 0
            val tiles = listOf(
                Tile.GrassTile(hasTree = true),
                Tile.GrassTile(),
                Tile.RoadVerticalTile,
                Tile.GrassTile(),
                Tile.GrassTile(),
                Tile.GrassTile(),
                Tile.StepTile(Tile.Orientation.VERTICAL, 1),
                Tile.GrassTile()
            )
            coEvery { getStepMessagesUseCase() } returns
                    flowOf(ResultMessages.Messages(messages))
            coEvery { getLastElephantPositionUseCase() } returns
                    flowOf(ResultElephantPosition.ElephantPosition(lastElephantPosition))
            coEvery { getTilesUseCase() } returns
                    flowOf(tiles)
            // When
            val getMapResult = getMapUseCase()
            // That
            assertThat(getMapResult.first()).isEqualTo(flowOf(ResultMap.Success(Map(tiles, 4))).first())
        }

    @Test
    fun `GetMapUseCase SHOULD update the messages of all StepTiles WHEN messages are sufficient`() =
        runBlockingTest {
            //Given
            val messages = listOf(
                Message("Step 1"),
                Message("Step 2"),
                Message("Step 3"),
                Message("Step 4"),
                Message("Step 5")
            )
            val tiles = listOf(
                Tile.StepTile(Tile.Orientation.VERTICAL, 1),
                Tile.StepTile(Tile.Orientation.VERTICAL, 2),
                Tile.StepTile(Tile.Orientation.VERTICAL, 3),
                Tile.StepTile(Tile.Orientation.VERTICAL, 4),
                Tile.StepTile(Tile.Orientation.VERTICAL, 5)
            )
            val lastElephantPosition = 0
            coEvery { getStepMessagesUseCase() } returns
                    flowOf(ResultMessages.Messages(messages))
            coEvery { getLastElephantPositionUseCase() } returns
                    flowOf(ResultElephantPosition.ElephantPosition(lastElephantPosition))
            coEvery { getTilesUseCase() } returns
                    flowOf(tiles)
            // When
            val resultMap = getMapUseCase()
            // That
            resultMap.filterIsInstance<ResultMap.Success>()
                .first().map.tiles.filterIsInstance<Tile.StepTile>().forEachIndexed { index, tile ->
                assertThat(tile.message).isEqualTo(messages[index].message)
            }
        }

    @Test
    fun `GetMapUseCase SHOULD return Map empty WHEN tiles use case is empty`() =
        runBlockingTest {
            // Give
            val messages = listOf(
                Message("Step 1"),
                Message("Step 2"),
                Message("Step 3"),
                Message("Step 4"),
                Message("Step 5")
            )
            val tiles = listOf<Tile>()
            val lastElephantPosition = 1
            coEvery { getStepMessagesUseCase() } returns
                    flowOf(ResultMessages.Messages(messages))
            coEvery { getLastElephantPositionUseCase() } returns
                    flowOf(ResultElephantPosition.ElephantPosition(lastElephantPosition))
            coEvery { getTilesUseCase() } returns
                    flowOf(tiles)
            // When
            val resultMap = getMapUseCase()
            // Then
            assertThat(resultMap.last()).isInstanceOf(ResultMap.MapIsEmpty::class.java)
        }

    @Test
    fun `GetMapUseCase SHOULD return Map Error WHEN getTilesUseCase returns error`() =
        runBlockingTest {
            // Give
            val messages = listOf(
                Message("Step 1"),
                Message("Step 2"),
                Message("Step 3"),
                Message("Step 4"),
                Message("Step 5")
            )
            val tiles = listOf<Tile>()
            val lastElephantPosition = 1
            coEvery { getStepMessagesUseCase() } returns
                    flowOf(ResultMessages.Messages(messages))
            coEvery { getLastElephantPositionUseCase() } returns
                    flowOf(ResultElephantPosition.ElephantPosition(lastElephantPosition))
            coEvery { getTilesUseCase() }.throws(Exception())
            // When
            val resultMap = getMapUseCase()
            // Then
            assertThat(resultMap.last()).isInstanceOf(ResultMap.Error::class.java)
        }
}