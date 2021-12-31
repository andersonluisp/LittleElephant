package br.andersonpimentel.littleelephant.map.view.adapter.delegates

import br.andersonpimentel.littleelephant.entities.Tile
import br.andersonpimentel.littleelephant.map.R
import br.andersonpimentel.littleelephant.map.databinding.ItemTileLayoutBinding
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