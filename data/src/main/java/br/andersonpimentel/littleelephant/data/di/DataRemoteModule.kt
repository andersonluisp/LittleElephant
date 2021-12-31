package br.andersonpimentel.littleelephant.data.di

import br.andersonpimentel.littleelephant.data.remote.api.ServerApi
import br.andersonpimentel.littleelephant.data.remote.source.RemoteDataSource
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory { providesOkHttpClient() }
    single { createWebService<ServerApi>(
        okHttpClient = get(),
    ) }

    factory { RemoteDataSource(serverApi = get()) }
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val baseUrl  ="https://cat-fact.herokuapp.com"
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}

fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}