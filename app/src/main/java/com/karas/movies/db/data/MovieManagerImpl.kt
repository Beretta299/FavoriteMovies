package com.karas.movies.db.data

import com.karas.movies.db.model.MovieEntity
import com.karas.movies.db.pojo.MovieData
import timber.log.Timber

class MovieManagerImpl(private val movieRepository: MovieRepository) : MovieManager {
    private val STEP = 50

    override suspend fun insertMovie(movieData: MovieData) {
        movieRepository.insertMovie(movieData.toMovieEntity())
    }

    override suspend fun loadNextPortion(currentItemsAmount: Int): List<MovieData> {
        val movieDataList = arrayListOf<MovieData>()
        for(entity in movieRepository.loadNextPortion(STEP, currentItemsAmount)) {
            movieDataList.add(entity.toMovieData())
        }
        return movieDataList
    }

    override suspend fun getMoviesCount(): Int {
        return movieRepository.getMoviesCount()
    }

    private fun MovieEntity.toMovieData(): MovieData {
        return MovieData(id?:-1, title, description, rank, imagePath)
    }

    private fun MovieData.toMovieEntity(): MovieEntity {
        return MovieEntity(id, title, description, rank, imagePath)
    }
}