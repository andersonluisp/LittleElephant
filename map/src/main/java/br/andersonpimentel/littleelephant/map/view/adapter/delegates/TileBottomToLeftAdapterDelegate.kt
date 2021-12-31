package br.andersonpimentel.littleelephant.map.view.adapter.delegates

import br.andersonpimentel.littleelephant.entities.Tile
import br.andersonpimentel.littleelephant.map.R
import br.andersonpimentel.littleelephant.map.databinding.ItemTileLayoutBinding
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileBottomToLeftAdapterDelegate {
    operator fun invoke(itemClickListener: (Tile.BottomToLeftTile) -> Unit) =
        adapterDelegateViewBinding<Tile.BottomToLeftTile, Tile, ItemTileLayoutBinding>(
            { layoutInflater, root -> ItemTileLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                Glide.with(context)
                    .load(R.drawable.tile_bottom_left)
                    .into(binding.ivTile)
            }
        }
}