package com.karas.movies.db.data

import com.karas.movies.db.model.MovieEntity

interface MovieRepository {
    suspend fun insertMovie(movieEntity: MovieEntity)

    suspend fun loadNextPortion(limit:Int, offset: Int): List<MovieEntity>

    suspend fun getMoviesCount(): Int
}