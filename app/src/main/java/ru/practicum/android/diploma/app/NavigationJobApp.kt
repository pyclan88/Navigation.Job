package ru.practicum.android.diploma.app

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding
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
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}
