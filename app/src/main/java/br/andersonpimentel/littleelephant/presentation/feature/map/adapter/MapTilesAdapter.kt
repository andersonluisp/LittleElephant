package br.andersonpimentel.littleelephant.presentation.feature.map.adapter

import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.presentation.feature.map.adapter.delegates.*
import br.andersonpimentel.littleelephant.presentation.feature.map.adapter.diffutil.TileDiffUtilCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MapTilesAdapter: AsyncListDifferDelegationAdapter<Tile>(TileDiffUtilCallback()) {

    init {
        with(delegatesManager){
            addDelegate(TileGrassAdapterDelegate{})
            addDelegate(TileStepAdapterDelegate{})
            addDelegate(TileBottomToLeftAdapterDelegate{})
            addDelegate(TileBottomToRightAdapterDelegate{})
            addDelegate(TileRoadHorizontalAdapterDelegate{})
            addDelegate(TileRoadVerticalAdapterDelegate{})
            addDelegate(TileTopToLeftAdapterDelegate{})
            addDelegate(TileTopToRightAdapterDelegate{})

        }
    }

}