package com.karas.movies

import android.app.Application
import com.karas.movies.di.AppComponent
import com.karas.movies.di.DaggerAppComponent
import com.karas.movies.di.modules.AppModule
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
        initTimber()
    }

    private fun initDI() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this.applicationContext))
            .build()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var appComponent:AppComponent? = null
        private set
    }
}