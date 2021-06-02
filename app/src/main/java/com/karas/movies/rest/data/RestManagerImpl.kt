package com.karas.movies.rest.data

import com.karas.movies.db.data.MovieManager
import com.karas.movies.pojo.MovieData
import com.karas.movies.rest.model.MovieRestModel
import timber.log.Timber

class RestManagerImpl(private val restRepository: RestRepository, private val movieManager: MovieManager) : RestManager {
    override suspend fun loadNewDataPortion(pageNumber: Int) {
        val movieDataList = arrayListOf<MovieData>()
        for(movie in restRepository.getPopularMovies(1).body()?.results?: listOf()) {
            movieDataList.add(movie.toMovieModel())
        }
        movieManager.insertMovies(movieDataList)
    }

    private fun MovieRestModel.toMovieModel() :MovieData{
        return MovieData(null, title, overview, vote_average, "$IMG_PREFIX$poster_path", release_date, false)
    }

    companion object {
        const val IMG_PREFIX = "https://image.tmdb.org/t/p/w185"
    }
}