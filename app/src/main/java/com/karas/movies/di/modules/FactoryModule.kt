package com.karas.movies.di.modules

import androidx.lifecycle.ViewModelProvider
import com.karas.movies.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface FactoryModule {
    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}