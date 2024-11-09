package ru.practicum.android.diploma.app

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NavigationJobApp : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NavigationJobApp)
            modules()
        }

    }

    companion object {
        private var instance: NavigationJobApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}
