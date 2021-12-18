package br.andersonpimentel.littleelephant.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elephant_position")
data class ElephantPosition(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var lastElephantPosition: Int = 0
)