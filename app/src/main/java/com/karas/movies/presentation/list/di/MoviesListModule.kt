package com.karas.movies.presentation.list.di

import androidx.lifecycle.ViewModel
import com.karas.movies.di.key.ViewModelKey
import com.karas.movies.presentation.MainViewModel
import com.karas.movies.presentation.list.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MoviesListModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    fun bindMainViewModel(viewModel: MoviesListViewModel): ViewModel
}