package com.karas.movies.rest.data

import com.karas.movies.pojo.MovieData

interface RestManager {
    suspend fun loadNewDataPortion(): List<MovieData>
    fun resetList()
}