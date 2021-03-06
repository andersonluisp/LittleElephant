package br.andersonpimentel.littleelephant.map.view.adapter.delegates

import androidx.core.view.isVisible
import br.andersonpimentel.littleelephant.entities.Tile
import br.andersonpimentel.littleelephant.map.R
import br.andersonpimentel.littleelephant.map.databinding.ItemTileLayoutBinding
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileGrassAdapterDelegate {
    operator fun invoke(itemClickListener: (Tile.GrassTile) -> Unit) =
        adapterDelegateViewBinding<Tile.GrassTile, Tile, ItemTileLayoutBinding>(
            { layoutInflater, root -> ItemTileLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                Glide.with(context)
                    .load(R.drawable.tile_grass)
                    .into(binding.ivTile)

                if (item.hasTree){
                    binding.lavTree.apply {
                        speed = 3f
                        isVisible = item.hasTree
                        setAnimation(R.raw.tree_lottie_animation)
                        repeatCount = LottieDrawable.INFINITE
                        repeatMode = LottieDrawable.REVERSE
                        playAnimation()
                    }
                } else{
                    binding.lavTree.apply {
                        cancelAnimation()
                        isVisible = item.hasTree
                    }
                }
            }
        }
}