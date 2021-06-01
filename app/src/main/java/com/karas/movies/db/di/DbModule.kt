package com.karas.movies.db.di

import android.content.Context
import androidx.room.Room
import com.karas.movies.db.AppDatabase
import com.karas.movies.db.data.MovieManager
import com.karas.movies.db.data.MovieManagerImpl
import com.karas.movies.db.data.MovieRepository
import com.karas.movies.db.data.MovieRepositoryImpl
import com.karas.movies.db.utils.DbContract
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        val storagePath = context.getExternalFilesDir(null)?.absolutePath
        val moviePath ="$storagePath/${DbContract.MovieData.TABLE_NAME}"
        val movieFolder = File(moviePath)
        if(movieFolder.exists()) {
            movieFolder.mkdir()
        }
        val dbName = "$moviePath/${DbContract.DB_NAME}"
        val dbBuilder = Room.databaseBuilder(context,
            AppDatabase::class.java,
            dbName)

        return dbBuilder.build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(appDatabase: AppDatabase) : MovieRepository{
        return MovieRepositoryImpl(appDatabase.moviesDao)
    }

    @Provides
    @Singleton
    fun provideMovieManager(movieRepository: MovieRepository): MovieManager {
        return MovieManagerImpl(movieRepository)
    }
}