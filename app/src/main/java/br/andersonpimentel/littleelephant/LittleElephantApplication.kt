package br.andersonpimentel.littleelephant

import android.app.Application
import br.andersonpimentel.littleelephant.di.dataModules
import br.andersonpimentel.littleelephant.di.domainModule
import br.andersonpimentel.littleelephant.map.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LittleElephantApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@LittleElephantApplication)
            modules(dataModules + presentationModule + domainModule)
        }
    }
}