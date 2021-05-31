package com.karas.movies.presentation.di

import androidx.lifecycle.ViewModel
import com.karas.movies.di.key.ViewModelKey
import com.karas.movies.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel):ViewModel
}