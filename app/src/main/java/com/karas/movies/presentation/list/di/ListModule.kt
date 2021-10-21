package com.karas.movies.presentation.list.di

import androidx.lifecycle.ViewModel
import com.karas.movies.di.key.ViewModelKey
import com.karas.movies.presentation.MainViewModel
import com.karas.movies.presentation.list.MoviesListViewModel
import com.karas.movies.presentation.list.rated.RatedListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ListModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    fun bindMainViewModel(viewModel: MoviesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RatedListViewModel::class)
    fun bindRatedViewModel(viewModel: RatedListViewModel): ViewModel
}