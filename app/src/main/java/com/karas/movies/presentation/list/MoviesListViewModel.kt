package com.karas.movies.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karas.movies.db.data.MovieManager
import com.karas.movies.db.pojo.MovieData
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val movieManager: MovieManager): ViewModel() {
    private val moviesLiveData = MutableLiveData<List<MovieData>>()
    private val minPreloadValue = 20
    private var lastApartmentId:Int? = -1
    private var currentItemsAmount = 0

    init {
        updateMoviesList()
    }

    fun updateMoviesList() {
        viewModelScope.launch {
            val nextPortion = movieManager.loadNextPortion(currentItemsAmount)
            if(nextPortion.isNotEmpty() && nextPortion.last().id != lastApartmentId){
                currentItemsAmount += nextPortion.size
                moviesLiveData.postValue(nextPortion)
            }
        }
    }

    fun getMoviesLiveData(): LiveData<List<MovieData>> {
        return moviesLiveData
    }

    fun checkIsAutoScrollNeeded(lastVisibleItemPosition: Int) {
        if(lastVisibleItemPosition >= (currentItemsAmount - minPreloadValue)){
            updateMoviesList()
        }
    }
}