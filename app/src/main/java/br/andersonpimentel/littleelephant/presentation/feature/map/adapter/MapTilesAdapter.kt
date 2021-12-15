package br.andersonpimentel.littleelephant.presentation.feature.map.adapter

import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.presentation.feature.map.adapter.delegates.*
import br.andersonpimentel.littleelephant.presentation.feature.map.adapter.diffutil.TileDiffUtilCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MapTilesAdapter: AsyncListDifferDelegationAdapter<Tile>(TileDiffUtilCallback()) {

    var elephantStepPosition: Tile.StepTile? = null

    init {
        with(delegatesManager){
            addDelegate(TileGrassAdapterDelegate{})
            addDelegate(TileStepAdapterDelegate{onChangeElephantStepPosition(it)})
            addDelegate(TileBottomToLeftAdapterDelegate{})
            addDelegate(TileBottomToRightAdapterDelegate{})
            addDelegate(TileRoadHorizontalAdapterDelegate{})
            addDelegate(TileRoadVerticalAdapterDelegate{})
            addDelegate(TileTopToLeftAdapterDelegate{})
            addDelegate(TileTopToRightAdapterDelegate{})
        }
    }

    override fun setItems(items: MutableList<Tile>?) {

        items?.filterIsInstance<Tile.StepTile>()?.firstOrNull() {it.hasElephant}.let {
            elephantStepPosition = it
        }

        super.setItems(items)
    }

    private fun onChangeElephantStepPosition(newElephantStepPosition: Tile.StepTile){
        elephantStepPosition.let {
            it?.hasElephant = false
            newElephantStepPosition.hasElephant = true
            notifyItemChanged(items.indexOf(it))
            notifyItemChanged(items.indexOf(newElephantStepPosition))
        }
        elephantStepPosition = newElephantStepPosition
    }

}