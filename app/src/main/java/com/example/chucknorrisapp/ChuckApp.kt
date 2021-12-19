package com.example.chucknorrisapp

import android.app.Application
import com.example.chucknorrisapp.di.appModule
import com.example.chucknorrisapp.di.networkModule
import com.example.chucknorrisapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ChuckApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ChuckApp)
            modules(listOf(networkModule, viewModelModule, appModule))
        }
    }
}