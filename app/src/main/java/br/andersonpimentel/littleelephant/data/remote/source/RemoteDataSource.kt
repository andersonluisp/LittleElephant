package br.andersonpimentel.littleelephant.data.remote.source

import android.util.Log
import br.andersonpimentel.littleelephant.data.remote.api.ServerApi
import br.andersonpimentel.littleelephant.data.remote.model.FactsResponse
import br.andersonpimentel.littleelephant.data.remote.util.mapRemoteErrors
import br.andersonpimentel.littleelephant.domain.responses.ResultRemote

class RemoteDataSource(private val serverApi: ServerApi) {
    suspend fun fetchMessages(): ResultRemote<FactsResponse>{
        Log.i("***LittleElephant", "RemoteDataSource | fetchMessages | Fetched Messages from API")
        return try {
            ResultRemote.Success(
                response = serverApi.fetchFacts()
            )
        } catch (throwable: Throwable){
            throwable.mapRemoteErrors()
        }
    }
}