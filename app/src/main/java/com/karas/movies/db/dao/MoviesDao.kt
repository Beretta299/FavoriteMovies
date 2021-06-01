package com.karas.movies.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.karas.movies.db.model.MovieEntity
import com.karas.movies.db.utils.DbContract

@Dao
interface MoviesDao {

    @Insert
    suspend fun insertMovie(entity: MovieEntity)

    @Query("SELECT * FROM ${DbContract.MovieData.TABLE_NAME} ORDER BY ${DbContract.MovieData.COLUMN_TITLE} ASC LIMIT :limit OFFSET :offset")
    suspend fun loadNextMoviesPortion(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT COUNT(*) FROM ${DbContract.MovieData.TABLE_NAME}")
    suspend fun getMoviesCount(): Int
}