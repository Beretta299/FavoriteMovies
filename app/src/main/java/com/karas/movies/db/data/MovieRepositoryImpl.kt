package com.karas.movies.db.data

import com.karas.movies.db.dao.MoviesDao
import com.karas.movies.db.model.MovieEntity

class MovieRepositoryImpl(private val moviesDao: MoviesDao) : MovieRepository{
    override suspend fun insertMovie(movieEntity: MovieEntity) {
        moviesDao.insertMovie(movieEntity)
    }

    override suspend fun loadNextPortion(limit: Int, offset: Int): List<MovieEntity> {
        return moviesDao.loadNextMoviesPortion(limit, offset)
    }

    override suspend fun insertMovies(movies: List<MovieEntity>) {
        moviesDao.insertMovie(*movies.toTypedArray())
    }

    override suspend fun getMoviesCount(): Int {
        return moviesDao.getMoviesCount()
    }

    override suspend fun rateMovie(movieID: Int, isLiked: Boolean) {
        moviesDao.rateMovie(movieID, isLiked)
    }
}