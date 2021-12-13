package br.andersonpimentel.littleelephant.presentation.feature.map.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import br.andersonpimentel.littleelephant.domain.entities.Tile

class TileDiffUtilCallback : DiffUtil.ItemCallback<Tile>() {
    override fun areItemsTheSame(oldItem: Tile, newItem: Tile): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Tile, newItem: Tile): Boolean {
        return oldItem == newItem
    }
}