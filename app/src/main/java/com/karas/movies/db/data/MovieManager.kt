package com.karas.movies.db.data

import com.karas.movies.pojo.MovieData

interface MovieManager {
    suspend fun insertMovie(movieData: MovieData)

    suspend fun insertMovies(movies: List<MovieData>)

    suspend fun loadNextPortion(currentItemsAmount: Int): List<MovieData>

    suspend fun getMoviesCount(): Int

    suspend fun rateMovie(movieID: Int, isLiked: Boolean)
}