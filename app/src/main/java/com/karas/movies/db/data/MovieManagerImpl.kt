package com.karas.movies.db.data

import com.karas.movies.db.model.MovieEntity
import com.karas.movies.pojo.MovieData

class MovieManagerImpl(private val movieRepository: MovieRepository) : MovieManager {
    private val STEP = 50

    override suspend fun insertMovie(movieData: MovieData) {
        movieRepository.insertMovie(movieData.toMovieEntity())
    }

    override suspend fun loadNextPortion(currentItemsAmount: Int): List<MovieData> {
        val movieDataList = arrayListOf<MovieData>()
        for(entity in movieRepository.loadNextPortion(STEP, 0)) {
            movieDataList.add(entity.toMovieData())
        }
        return movieDataList
    }

    override suspend fun getSavedMovies(): List<MovieData> {
        val movieDataList = arrayListOf<MovieData>()
        for(entity in movieRepository.getSavedMovies()) {
            movieDataList.add(entity.toMovieData())
        }
        return movieDataList
    }

    override suspend fun insertMovies(movies: List<MovieData>) {
        val entities = arrayListOf<MovieEntity>()
        for(movie in movies) {
            entities.add(movie.toMovieEntity())
        }
        movieRepository.insertMovies(entities)
    }

    override suspend fun getMoviesCount(): Int {
        return movieRepository.getMoviesCount()
    }

    override suspend fun rateMovie(movieID: Int, isLiked: Boolean) {
        movieRepository.rateMovie(movieID, isLiked)
    }

    override suspend fun getRatedMovies(): List<MovieData> {
        val movieData = arrayListOf<MovieData>()
        for(movie in movieRepository.getRatedMovies()){
            movieData.add(movie.toMovieData())
        }
        return movieData
    }

    override suspend fun checkIsMovieLiked(movieModel: MovieData): Boolean {
        return movieRepository.checkIsMovieLiked(movieModel)
    }

    private fun MovieEntity.toMovieData(): MovieData {
        return MovieData(id?:-1, title, description, rank, imagePath, releaseDate, isLiked)
    }

    private fun MovieData.toMovieEntity(): MovieEntity {
        return MovieEntity(id, title, description, rank, imagePath, releaseDate, isLiked)
    }
}