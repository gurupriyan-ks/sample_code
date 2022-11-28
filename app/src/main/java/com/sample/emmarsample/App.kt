package com.sample.emmarsample

import android.app.Application
import com.sample.emmarsample.di.appModule
import com.sample.emmarsample.di.dbModule
import com.sample.emmarsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(appModule, viewModelModule, dbModule))
        }

    }
}