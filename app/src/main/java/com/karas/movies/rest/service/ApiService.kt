package com.karas.movies.rest.service

import com.karas.movies.rest.model.MovieRestResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/now_playing")
    suspend fun getPopularMovies(@Query("page") pageNumber: Int, @Query("api_key") apiKey: String = "e2ece7591636efcd15dd2aa1d78d8e00", @Query("language") language:String = "en-US"): Response<MovieRestResults>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
    }
}