package com.karas.movies.presentation.list.rated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karas.movies.db.data.MovieManager
import com.karas.movies.pojo.MovieData
import com.karas.movies.presentation.utils.Loaded
import com.karas.movies.presentation.utils.Loading
import com.karas.movies.presentation.utils.UIStages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RatedListViewModel @Inject constructor(private val movieManager: MovieManager): ViewModel() {
    private val moviesLiveData = MutableLiveData<Pair<List<MovieData>, Boolean>>()
    private val uiStagesLiveData = MutableLiveData<UIStages>()

    init {
        initMoviesList()
    }

    private fun initMoviesList() {
        uiStagesLiveData.postValue(Loading)
        viewModelScope.launch {
            val nextPortion = movieManager.getSavedMovies()
            if(nextPortion.isNotEmpty()){
                moviesLiveData.postValue(Pair(nextPortion, false))
            }
            uiStagesLiveData.postValue(Loaded)
        }
    }

    fun getMoviesLiveData(): LiveData<Pair<List<MovieData>,Boolean>> {
        return moviesLiveData
    }

    fun getUIStages(): LiveData<UIStages> {
        return uiStagesLiveData
    }

    fun refreshData() {
        uiStagesLiveData.postValue(Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val nextPortion = movieManager.getRatedMovies()
            if(nextPortion.isNotEmpty()){
                moviesLiveData.postValue(Pair(nextPortion, true))
            } else {
                moviesLiveData.postValue(Pair(arrayListOf(), true))
            }
            uiStagesLiveData.postValue(Loaded)
        }
    }

    fun rateMovie(movieData: MovieData) {
        viewModelScope.launch {
            movieManager.insertMovie(movieData)
        }
        refreshData()
    }
}