package ru.practicum.android.diploma.app

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.practicum.android.diploma.di.UseCaseModule
import ru.practicum.android.diploma.di.dataModule
import ru.practicum.android.diploma.di.repositoryModule
import ru.practicum.android.diploma.di.utilModule
import ru.practicum.android.diploma.di.viewModelModule

class NavigationJobApp : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NavigationJobApp)
            modules(dataModule, UseCaseModule, repositoryModule, viewModelModule, utilModule)
        }

    }

    companion object {
        private var instance: NavigationJobApp? = null
        const val HEAD_HUNTER_TOKEN = "APPLKBT2B63I20SQJH2RCL03Q00G1V5KKKJ9NE2M8J5OIOJGNDDQF5KLEV0VG519"

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}
