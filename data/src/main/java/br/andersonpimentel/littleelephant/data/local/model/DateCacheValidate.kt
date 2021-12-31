package br.andersonpimentel.littleelephant.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date_validate")
data class DateCacheValidate(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var lastUpdateDate: Long? = null
)

data class AppDate(
    val day: Int,
    val month: Int,
    val year: Int
)