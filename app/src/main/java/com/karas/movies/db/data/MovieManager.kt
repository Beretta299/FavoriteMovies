package com.karas.movies.db.data

import com.karas.movies.db.pojo.MovieData

interface MovieManager {
    suspend fun insertMovie(movieData: MovieData)

    suspend fun loadNextPortion(currentItemsAmount: Int): List<MovieData>

    suspend fun getMoviesCount(): Int
}