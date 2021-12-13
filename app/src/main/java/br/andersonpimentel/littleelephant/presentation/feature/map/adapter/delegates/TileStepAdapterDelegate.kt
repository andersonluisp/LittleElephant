package br.andersonpimentel.littleelephant.presentation.feature.map.adapter.delegates

import android.widget.AdapterView
import br.andersonpimentel.littleelephant.R
import br.andersonpimentel.littleelephant.databinding.ItemStepLayoutBinding
import br.andersonpimentel.littleelephant.databinding.ItemTileLayoutBinding
import br.andersonpimentel.littleelephant.domain.entities.Tile
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object TileStepAdapterDelegate {
    operator fun invoke(itemClickListener: (Tile.StepTile) -> Unit) =
        adapterDelegateViewBinding<Tile.StepTile, Tile, ItemStepLayoutBinding>(
            { layoutInflater, root -> ItemStepLayoutBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                val stepTileDrawable =
                    if (item.orientation == Tile.Orientation.VERTICAL)
                        R.drawable.tile_vertical_step
                    else
                        R.drawable.tile_horizontal_step

                Glide.with(context)
                    .load(stepTileDrawable)
                    .into(binding.ivTile)

                binding.tvStepNumber.text = item.step.toString()
            }
        }
}