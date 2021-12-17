package br.andersonpimentel.littleelephant.data.remote.api

import br.andersonpimentel.littleelephant.data.remote.model.FactsResponse
import retrofit2.http.GET

interface ServerApi {
    @GET("/facts")
    suspend fun fetchFacts(): FactsResponse
}