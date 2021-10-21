package com.karas.movies.presentation.adapters.utils

import com.karas.movies.pojo.MovieData

interface RateMovieListener {
    fun rateMovie(movieData: MovieData)
}