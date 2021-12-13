package br.andersonpimentel.littleelephant.presentation.feature.map.adapter.delegates

import androidx.core.view.isVisible
import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.databinding.ItemTileLayoutBinding
import br.andersonpimentel.littleelephant.domain.entities.Tile
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