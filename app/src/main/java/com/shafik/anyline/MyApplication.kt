package com.shafik.anyline

import android.app.Application
import com.shafik.anyline.di.networkModule
import com.shafik.anyline.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val modulesList = listOf(networkModule, repositoryModule)
        startKoin {
            androidContext(this@MyApplication)
            modules(modulesList)
        }
    }
}