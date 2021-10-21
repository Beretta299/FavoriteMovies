package com.karas.movies.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karas.movies.db.dao.MoviesDao
import com.karas.movies.db.model.MovieEntity

@Database(
    version = 1,
    entities = [MovieEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}