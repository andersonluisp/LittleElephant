package br.andersonpimentel.littleelephant.map.view.adapter

import android.view.View
import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.map.view.adapter.delegates.*
import br.andersonpimentel.littleelephant.map.view.adapter.diffutil.TileDiffUtilCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MapTilesAdapter(showToolTip: (View, Tile.StepTile) -> Unit) :
    AsyncListDifferDelegationAdapter<Tile>(TileDiffUtilCallback()) {

    private var elephantStepPosition: Tile.StepTile? = null

    init {
        with(delegatesManager) {
            addDelegate(TileGrassAdapterDelegate {})
            addDelegate(
                TileStepAdapterDelegate(itemClickListener = { view, item ->
                    showToolTip(view, item)
                    onChangeElephantStepPosition(item)
                })
            )
            addDelegate(TileBottomToLeftAdapterDelegate {})
            addDelegate(TileBottomToRightAdapterDelegate {})
            addDelegate(TileRoadHorizontalAdapterDelegate {})
            addDelegate(TileRoadVerticalAdapterDelegate {})
            addDelegate(TileTopToLeftAdapterDelegate {})
            addDelegate(TileTopToRightAdapterDelegate {})
        }
    }

    override fun setItems(items: MutableList<Tile>?) {

        items?.filterIsInstance<Tile.StepTile>()?.firstOrNull() { it.hasElephant }.let {
            elephantStepPosition = it
        }

        super.setItems(items)
    }

    private fun onChangeElephantStepPosition(newElephantStepPosition: Tile.StepTile) {
        elephantStepPosition.let {
            it?.hasElephant = false
            newElephantStepPosition.hasElephant = true
            notifyItemChanged(items.indexOf(it))
            notifyItemChanged(items.indexOf(newElephantStepPosition))
        }
        elephantStepPosition = newElephantStepPosition
    }

}