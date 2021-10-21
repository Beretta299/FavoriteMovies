package com.karas.movies.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karas.movies.db.model.MovieEntity
import com.karas.movies.db.utils.DbContract

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg entity: MovieEntity)

    @Query("SELECT * FROM ${DbContract.MovieData.TABLE_NAME} ORDER BY ${DbContract.MovieData.COLUMN_TITLE} ASC LIMIT :limit OFFSET :offset")
    suspend fun loadNextMoviesPortion(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT COUNT(*) FROM ${DbContract.MovieData.TABLE_NAME}")
    suspend fun getMoviesCount(): Int

    @Query("SELECT * FROM ${DbContract.MovieData.TABLE_NAME} LIMIT 50")
    suspend fun getSavedMovies(): List<MovieEntity>

    @Query("SELECT * FROM ${DbContract.MovieData.TABLE_NAME} WHERE ${DbContract.MovieData.COLUMN_IS_LIKED} = 1")
    suspend fun getRatedMovies(): List<MovieEntity>

    @Query("UPDATE ${DbContract.MovieData.TABLE_NAME} SET ${DbContract.MovieData.COLUMN_IS_LIKED}=:liked WHERE ${DbContract.MovieData.COLUMN_ID}=:movieID")
    suspend fun rateMovie(movieID: Int, liked: Boolean)

    @Query("SELECT * FROM ${DbContract.MovieData.TABLE_NAME} WHERE ${DbContract.MovieData.COLUMN_TITLE} = :title AND ${DbContract.MovieData.COLUMN_ICON_PATH}=:imagePath")
    suspend fun checkIsMovieLiked(imagePath: String, title: String): List<MovieEntity>
}