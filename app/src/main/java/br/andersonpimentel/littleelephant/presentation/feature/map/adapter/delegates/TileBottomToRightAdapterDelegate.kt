package br.andersonpimentel.littleelephant.presentation.feature.map.adapter.delegates

import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.databinding.ItemTileLayoutBinding
import br.andersonpimentel.littleelephant.domain.entities.Tile
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileBottomToRightAdapterDelegate {
    operator fun invoke(itemClickListener: (Tile.BottomToRightTile) -> Unit) =
        adapterDelegateViewBinding<Tile.BottomToRightTile, Tile, ItemTileLayoutBinding>(
            { layoutInflater, root -> ItemTileLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                Glide.with(context)
                    .load(R.drawable.tile_bottom_right)
                    .into(binding.ivTile)
            }
        }
}