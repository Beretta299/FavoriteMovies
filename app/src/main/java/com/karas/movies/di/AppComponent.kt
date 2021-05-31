package com.karas.movies.di

import com.karas.movies.di.annotations.ApplicationContext
import com.karas.movies.di.modules.AppModule
import com.karas.movies.di.modules.FactoryModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, FactoryModule::class])
@Singleton
@ApplicationContext
interface AppComponent {
}