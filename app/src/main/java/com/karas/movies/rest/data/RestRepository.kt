package com.karas.movies.rest.data

import com.karas.movies.rest.model.MovieRestResults
import retrofit2.Response

interface RestRepository {
    suspend fun getPopularMovies(pageNumber: Int): Response<MovieRestResults>
}