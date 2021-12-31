package br.andersonpimentel.littleelephant.map.view.adapter.delegates

import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.map.R
import br.andersonpimentel.littleelephant.map.databinding.ItemTileLayoutBinding
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileTopToRightAdapterDelegate {
    operator fun invoke(itemClickListener: (Tile.TopToRightTile) -> Unit) =
        adapterDelegateViewBinding<Tile.TopToRightTile, Tile, ItemTileLayoutBinding>(
            { layoutInflater, root -> ItemTileLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                Glide.with(context)
                    .load(R.drawable.tile_top_right)
                    .into(binding.ivTile)
            }
        }
}