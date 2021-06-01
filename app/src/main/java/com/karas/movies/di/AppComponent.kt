package com.karas.movies.di

import com.karas.movies.arch.DependenciesLoaderFragment
import com.karas.movies.di.annotations.ApplicationContext
import com.karas.movies.di.modules.AppModule
import com.karas.movies.di.modules.FactoryModule
import com.karas.movies.presentation.di.MainModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, FactoryModule::class, MainModule::class])
@Singleton
@ApplicationContext
interface AppComponent {
    fun inject(dependenciesLoaderFragment: DependenciesLoaderFragment)
}