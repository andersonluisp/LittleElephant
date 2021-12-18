package br.andersonpimentel.littleelephant.data.local.util

import br.andersonpimentel.littleelephant.data.local.model.AppDate
import br.andersonpimentel.littleelephant.data.local.model.DateCacheValidate
import br.andersonpimentel.littleelephant.data.local.model.ElephantPosition
import br.andersonpimentel.littleelephant.data.local.model.MessagesCache
import br.andersonpimentel.littleelephant.domain.entities.Message
import br.andersonpimentel.littleelephant.domain.entities.Tile
import java.text.SimpleDateFormat
import java.util.*

fun List<Message>.modelToMessageCache(): List<MessagesCache> {
    val listMessages = mutableListOf<MessagesCache>()
    this.map {
        listMessages.add(
            MessagesCache(
                message = it.message
            )
        )
    }
    return listMessages
}

fun List<MessagesCache>.toModel(): List<Message> {
    val listMessages = mutableListOf<Message>()
    this.map {
        listMessages.add(
            Message(
                message = it.message
            )
        )
    }
    return listMessages
}

fun Date.toAppDate(): AppDate {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    val currentDateString = formatter.format(this)
    val currentDateSplit = currentDateString.split("/")
    return AppDate(
        day = currentDateSplit[0].toInt(),
        month = currentDateSplit[1].toInt(),
        year = currentDateSplit[2].toInt()
    )
}

fun AppDate.yearsDiff(appDate: AppDate): Int{
    return this.year - appDate.year
}

fun AppDate.isYearsEquals(appDate: AppDate): Boolean{
    return this.year - appDate.year == 0
}

fun AppDate.monthDiff(appDate: AppDate): Int{
    return this.month - appDate.month
}

fun AppDate.isMonthsEquals(appDate: AppDate): Boolean{
    return this.month - appDate.month == 0
}

fun AppDate.dayDiff(appDate: AppDate): Int{
    return this.day - appDate.day
}

fun Tile.StepTile.toElephantPosition() : ElephantPosition{
    return ElephantPosition(
        lastElephantPosition = this.step
    )
}