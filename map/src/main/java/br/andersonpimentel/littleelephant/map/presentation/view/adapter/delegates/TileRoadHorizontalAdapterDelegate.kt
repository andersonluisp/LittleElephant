package br.andersonpimentel.littleelephant.map.presentation.view.adapter.delegates

import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.map.R
import br.andersonpimentel.littleelephant.map.databinding.ItemTileLayoutBinding
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileRoadHorizontalAdapterDelegate {
    operator fun invoke(itemClickListener: (Tile.RoadHorizontalTile) -> Unit) =
        adapterDelegateViewBinding<Tile.RoadHorizontalTile, Tile, ItemTileLayoutBinding>(
            { layoutInflater, root -> ItemTileLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                Glide.with(context)
                    .load(R.drawable.tile_horizontal_road)
                    .into(binding.ivTile)
            }
        }
}