package br.andersonpimentel.littleelephant.presentation.feature.map.adapter.delegates

import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.databinding.ItemTileLayoutBinding
import br.andersonpimentel.littleelephant.domain.entities.Tile
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileRoadVerticalAdapterDelegate {
    operator fun invoke(itemClickListener: (Tile.RoadVerticalTile) -> Unit) =
        adapterDelegateViewBinding<Tile.RoadVerticalTile, Tile, ItemTileLayoutBinding>(
            { layoutInflater, root -> ItemTileLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                Glide.with(context)
                    .load(R.drawable.tile_vertical_road)
                    .into(binding.ivTile)
            }
        }
}