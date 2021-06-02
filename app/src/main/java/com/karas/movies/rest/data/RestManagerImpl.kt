package com.karas.movies.rest.data

import com.karas.movies.db.data.MovieManager
import com.karas.movies.pojo.MovieData
import com.karas.movies.rest.model.MovieRestModel
import timber.log.Timber

class RestManagerImpl(private val restRepository: RestRepository, private val movieManager: MovieManager) : RestManager {
    private var currentPage = 0
    override suspend fun loadNewDataPortion(): List<MovieData> {
        val movieDataList = arrayListOf<MovieData>()
        currentPage++
        for(movie in restRepository.getPopularMovies(currentPage).body()?.results?: listOf()) {
            val movieModel = movie.toMovieModel()
            movieModel.isLiked = movieManager.checkIsMovieLiked(movieModel)
            movieDataList.add(movieModel)
        }
        return movieDataList
    }

    override fun resetList() {
        currentPage = 0
    }

    private fun MovieRestModel.toMovieModel() :MovieData{
        return MovieData(null, title, overview, vote_average, "$IMG_PREFIX$poster_path", release_date, false)
    }

    companion object {
        const val IMG_PREFIX = "https://image.tmdb.org/t/p/w185"
    }
}