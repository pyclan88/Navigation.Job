package ru.practicum.android.diploma.app

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.practicum.android.diploma.di.dataModule
import ru.practicum.android.diploma.di.interactorModule
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
            modules(dataModule, interactorModule, repositoryModule, viewModelModule, utilModule)
        }

    }

    companion object {
        private var instance: NavigationJobApp? = null
        private const val HEAD_HUNTER_TOKEN = " APPLG17UBFH0BCT1O3OR2N2OU8CVKM03I33K0TLLMSU07HDKJVGF3ICCJCV6ND0N"

        fun getToken(): String {
            return HEAD_HUNTER_TOKEN
        }

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}
