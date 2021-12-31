package br.andersonpimentel.littleelephant.remote.api

import br.andersonpimentel.littleelephant.remote.model.FactsResponse
import retrofit2.http.GET

interface ServerApi {
    @GET("/facts")
    suspend fun fetchFacts(): FactsResponse
}