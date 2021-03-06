package br.andersonpimentel.littleelephant.remote.source

import android.util.Log
import br.andersonpimentel.littleelephant.remote.api.ServerApi
import br.andersonpimentel.littleelephant.remote.model.FactsResponse
import br.andersonpimentel.littleelephant.remote.util.mapRemoteErrors
import br.andersonpimentel.littleelephant.responses.ResultRemote

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