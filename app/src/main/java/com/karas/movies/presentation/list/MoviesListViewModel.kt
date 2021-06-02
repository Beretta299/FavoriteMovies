package com.karas.movies.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karas.movies.db.data.MovieManager
import com.karas.movies.pojo.MovieData
import com.karas.movies.presentation.utils.Loaded
import com.karas.movies.presentation.utils.Loading
import com.karas.movies.presentation.utils.UIStages
import com.karas.movies.rest.data.RestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val movieManager: MovieManager, private val restManager: RestManager): ViewModel() {
    private val moviesLiveData = MutableLiveData<Pair<List<MovieData>, Boolean>>()
    private val uiStagesLiveData = MutableLiveData<UIStages>()
    private val minPreloadValue = 20
    private var lastApartmentId:Int? = -1
    private var currentItemsAmount = 0

    init {
        initMoviesList()
    }

    private fun initMoviesList() {
        uiStagesLiveData.postValue(Loading)
        viewModelScope.launch {
            val nextPortion = movieManager.getSavedMovies()
            if(nextPortion.isNotEmpty()){
                if( nextPortion.last().id != lastApartmentId) {
                    currentItemsAmount += nextPortion.size
                    moviesLiveData.postValue(Pair(nextPortion, false))
                    uiStagesLiveData.postValue(Loaded)
                }
            } else {
                loadMoreData()
            }
        }
    }

    fun getMoviesLiveData(): LiveData<Pair<List<MovieData>,Boolean>> {
        return moviesLiveData
    }

    fun getUIStages(): LiveData<UIStages> {
        return uiStagesLiveData
    }

    fun checkIsAutoScrollNeeded(lastVisibleItemPosition: Int) {
        if(lastVisibleItemPosition >= (currentItemsAmount - minPreloadValue)){
            loadMoreData()
        }
    }

    private fun loadMoreData() {
        uiStagesLiveData.postValue(Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val nextPortion = restManager.loadNewDataPortion()
            if(nextPortion.isNotEmpty() && nextPortion.last().id != lastApartmentId){
                currentItemsAmount += nextPortion.size
                moviesLiveData.postValue(Pair(nextPortion, false))
                uiStagesLiveData.postValue(Loaded)
            }
        }
    }

    fun refreshList() {
        uiStagesLiveData.postValue(Loading)
        viewModelScope.launch(Dispatchers.IO) {
            currentItemsAmount = 0
            restManager.resetList()
            val nextPortion = restManager.loadNewDataPortion()
            if(nextPortion.isNotEmpty() && nextPortion.last().id != lastApartmentId){
                Timber.v("NextPortion last id %s", nextPortion.first().title)
                currentItemsAmount += nextPortion.size
                moviesLiveData.postValue(Pair(nextPortion, true))
                uiStagesLiveData.postValue(Loaded)
            }
        }
    }

    fun rateMovie(movieData: MovieData) {
        viewModelScope.launch {
            movieManager.insertMovie(movieData)
        }
    }
}