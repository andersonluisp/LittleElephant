package br.andersonpimentel.littleelephant.map.view.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import br.andersonpimentel.littleelephant.entities.Tile

class TileDiffUtilCallback : DiffUtil.ItemCallback<Tile>() {
    override fun areItemsTheSame(oldItem: Tile, newItem: Tile): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Tile, newItem: Tile): Boolean {
        return oldItem == newItem
    }
}