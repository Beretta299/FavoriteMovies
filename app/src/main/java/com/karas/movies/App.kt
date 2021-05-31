package com.karas.movies

import android.app.Application
import com.karas.movies.di.AppComponent
import com.karas.movies.di.DaggerAppComponent
import com.karas.movies.di.modules.AppModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this.applicationContext))
            .build()
    }

    companion object {
        var appComponent:AppComponent? = null
        private set
    }
}