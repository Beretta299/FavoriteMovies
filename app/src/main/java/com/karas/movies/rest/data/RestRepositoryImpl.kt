package com.karas.movies.rest.data

import com.karas.movies.rest.model.MovieRestResults
import com.karas.movies.rest.service.ApiService
import retrofit2.Response

class RestRepositoryImpl(private val apiService: ApiService): RestRepository {
    override suspend fun getPopularMovies(pageNumber: Int): Response<MovieRestResults> {
        return apiService.getPopularMovies(pageNumber)
    }
}