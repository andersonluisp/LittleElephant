package br.andersonpimentel.littleelephant.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessagesCache(
    @PrimaryKey(autoGenerate = true)
    var messageId: Int = 0,
    var message: String = ""
)