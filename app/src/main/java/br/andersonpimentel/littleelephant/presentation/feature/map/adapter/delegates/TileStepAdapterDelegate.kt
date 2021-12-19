package br.andersonpimentel.littleelephant.presentation.feature.map.adapter.delegates

import android.transition.TransitionManager
import android.view.View
import androidx.core.view.isVisible
import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.databinding.ItemStepLayoutBinding
import br.andersonpimentel.littleelephant.domain.entities.Tile
import br.andersonpimentel.littleelephant.presentation.util.load
import com.airbnb.lottie.LottieDrawable
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileStepAdapterDelegate {
    operator fun invoke(spanCount: Int, itemClickListener: (View, Tile.StepTile) -> Unit) =
        adapterDelegateViewBinding<Tile.StepTile, Tile, ItemStepLayoutBinding>(
            { layoutInflater, root -> ItemStepLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            setupTileSize(spanCount, binding)
            setupElephantSize(binding)
            bind {
                binding.apply {
                    ivTile.load(setStepTileDrawable(item))
                    tvStepNumber.text = item.step.toString()
                    root.setOnClickListener {
                        itemClickListener(root, item)
                    }
                    showElephant(item, this)
                }
            }
        }

    private fun showElephant(
        item: Tile.StepTile,
        binding: ItemStepLayoutBinding
    ) {
        binding.apply {
            TransitionManager.beginDelayedTransition(binding.elephantContainer)
            lavElephant.apply {
                isVisible = item.hasElephant
                if (item.hasElephant) {
                    setAnimation(R.raw.elephant_lottie_animation)
                    repeatCount = LottieDrawable.INFINITE
                    playAnimation()
                } else {
                    cancelAnimation()
                }
            }
        }
    }

    private fun setStepTileDrawable(item: Tile.StepTile): Int {
        return if (item.orientation == Tile.Orientation.VERTICAL)
            R.drawable.tile_vertical_step
        else
            R.drawable.tile_horizontal_step
    }

    private fun setupTileSize(spanCount: Int, binding: ItemStepLayoutBinding){
        val widthScreenSize = binding.root.resources.displayMetrics.widthPixels
        val layoutParams = binding.itemStepLayout.layoutParams
        layoutParams.width = widthScreenSize / spanCount
        layoutParams.height = widthScreenSize / spanCount
        binding.itemStepLayout.layoutParams = layoutParams
    }

    private fun setupElephantSize(binding: ItemStepLayoutBinding){
        val tileLayoutParams = binding.itemStepLayout.layoutParams
        val elephantParams = binding.lavElephant.layoutParams
        elephantParams.width = tileLayoutParams.width / 2
        elephantParams.height = tileLayoutParams.height / 2
        binding.lavElephant.layoutParams = elephantParams
    }
}