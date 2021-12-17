package br.andersonpimentel.littleelephant.core

import android.app.Application
import br.andersonpimentel.littleelephant.BuildConfig
import br.andersonpimentel.littleelephant.data.di.remoteDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LittleElephantApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@LittleElephantApplication)
            modules(listOf(remoteDataSourceModule))
        }
    }
}