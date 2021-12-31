package br.andersonpimentel.littleelephant.map.presentation.view.adapter.delegates

import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.map.R
import br.andersonpimentel.littleelephant.map.databinding.ItemTileLayoutBinding
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