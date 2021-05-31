package com.karas.movies.di.modules

import android.content.Context
import com.karas.movies.di.annotations.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return context
    }
}