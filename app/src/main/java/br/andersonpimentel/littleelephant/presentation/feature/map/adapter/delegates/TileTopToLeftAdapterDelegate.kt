package br.andersonpimentel.littleelephant.presentation.feature.map.adapter.delegates

import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.databinding.ItemTileLayoutBinding
import br.andersonpimentel.littleelephant.domain.entities.Tile
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileTopToLeftAdapterDelegate {
    operator fun invoke(itemClickListener: (Tile.TopToLeftTile) -> Unit) =
        adapterDelegateViewBinding<Tile.TopToLeftTile, Tile, ItemTileLayoutBinding>(
            { layoutInflater, root -> ItemTileLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                Glide.with(context)
                    .load(R.drawable.tile_top_left)
                    .into(binding.ivTile)
            }
        }
}