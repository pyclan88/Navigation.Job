package ru.practicum.android.diploma.app

import android.app.Application
import android.content.Context

class NavigationJobApp : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

    }

    companion object {
        private var instance: NavigationJobApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}
