package com.example.moodimvvmexample

import android.app.Application
import com.example.moodimvvmexample.di.MoodiAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoodiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Koin을 통한 종속성 주입
        startKoin {
            //androidLogger(Level.ERROR)
            androidContext(this@MoodiApplication)
            modules(MoodiAppModule)
        }
    }
}