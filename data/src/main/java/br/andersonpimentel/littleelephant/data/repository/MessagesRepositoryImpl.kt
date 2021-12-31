package br.andersonpimentel.littleelephant.data.repository

import android.util.Log
import br.andersonpimentel.littleelephant.data.local.model.DateCacheValidate
import br.andersonpimentel.littleelephant.data.local.source.DateCacheValidateDataSource
import br.andersonpimentel.littleelephant.data.local.source.MessagesCacheDataSource
import br.andersonpimentel.littleelephant.data.local.util.*
import br.andersonpimentel.littleelephant.data.remote.source.RemoteDataSource
import br.andersonpimentel.littleelephant.data.remote.util.SuccessRemoteFetch
import br.andersonpimentel.littleelephant.data.remote.util.toMessageModel
import br.andersonpimentel.littleelephant.domain.entities.Message
import br.andersonpimentel.littleelephant.domain.repository.MessagesRepository
import br.andersonpimentel.littleelephant.domain.responses.ResultRemote
import br.andersonpimentel.littleelephant.domain.responses.ResultRequired
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*

class MessagesRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val messagesCacheDataSource: MessagesCacheDataSource,
    private val dateCacheValidateDataSource: DateCacheValidateDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val onSuccessRemoteFetch: SuccessRemoteFetch
): MessagesRepository {

    override suspend fun getMessages(): Flow<ResultRequired<List<Message>>> {
        return if(shouldFetchRemoteMessages()){
            flowOf(getFactsRemote())
        } else {
            messagesCacheDataSource.getMessages()
                .map { cacheList ->
                    val result = when {
                        cacheList.isEmpty() -> getFactsRemote()
                        else -> {
                            val messages = cacheList.toModel()
                            ResultRequired.Success(messages)
                        }
                    }
                    result
                }
        }
    }

    private suspend fun getFactsRemote(): ResultRequired<List<Message>> {
        return withContext(dispatcher) {
            when (val resultRemote = remoteDataSource.fetchMessages()) {
                is ResultRemote.Success -> {
                    onSuccessRemoteFetch.sendSuccessMessage()
                    val mappedList = resultRemote.response.toMessageModel()
                    val currentDate = Calendar.getInstance().timeInMillis
                    messagesCacheDataSource.updateMessages(mappedList.modelToMessageCache())
                    dateCacheValidateDataSource.updateDateCacheValidate(
                        DateCacheValidate(
                            lastUpdateDate = currentDate
                        )
                    )
                    ResultRequired.Success(result = mappedList)
                }
                is ResultRemote.ErrorResponse -> {
                    ResultRequired.Error(resultRemote.throwable)
                }
            }
        }
    }

    private suspend fun shouldFetchRemoteMessages(): Boolean {
        val lastDateFetchMessages = try {
            dateCacheValidateDataSource.getLastDateFetchMessages().first().lastUpdateDate?.let {
                Date(it)
            }
        } catch (t: Throwable){
            Log.i(
                "***LittleElephant",
                "MessagesRepository | shouldFetchRemoteMessages | It's the first messages request"
            )
            null
        }
        val mappedCurrentDate = Calendar.getInstance().time.toAppDate()
        val mappedLastDateFetchMessages = lastDateFetchMessages?.toAppDate()
        return if (mappedLastDateFetchMessages != null) {
            Log.i("***LittleElephant", "MessagesRepository | shouldFetchRemoteMessages | Current Date: $mappedCurrentDate")
            Log.i("***LittleElephant", "MessagesRepository | shouldFetchRemoteMessages | Last Date Fetched: $mappedLastDateFetchMessages")
            when {
                (mappedCurrentDate.yearsDiff(mappedLastDateFetchMessages) >= 1) -> true

                (mappedCurrentDate.monthDiff(mappedLastDateFetchMessages) >= 1 &&
                        mappedCurrentDate.isYearsEquals(mappedLastDateFetchMessages)) -> true

                (mappedCurrentDate.dayDiff(mappedLastDateFetchMessages) >= 1 &&
                        mappedCurrentDate.isMonthsEquals(mappedLastDateFetchMessages) &&
                        mappedCurrentDate.isYearsEquals(mappedLastDateFetchMessages)) -> true

                else -> false
            }
        } else {
            true
        }
    }
}