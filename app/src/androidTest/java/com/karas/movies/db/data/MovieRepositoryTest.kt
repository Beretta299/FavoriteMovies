package com.karas.movies.db.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.karas.movies.db.AppDatabase
import com.karas.movies.db.dao.MoviesDao
import com.karas.movies.db.data.util.UserRepositoryDataSource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieRepositoryTest {
    private lateinit var movieDao: MoviesDao
    private lateinit var db: AppDatabase
    private lateinit var movieRepository: MovieRepository

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDao = db.moviesDao
        movieRepository = MovieRepositoryImpl(movieDao)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeMovieAndCheckIsNotEmpty() = runBlocking {
        movieRepository.insertMovie(UserRepositoryDataSource.FAKE_MOVIE_1)
        assert(movieRepository.getSavedMovies().isNotEmpty())
    }

    @Test
    fun writeLikedMoviesAndThatTheyLiked() = runBlocking {
        movieRepository.insertMovies(listOf(UserRepositoryDataSource.FAKE_MOVIE_LIKED_2,
            UserRepositoryDataSource.FAKE_MOVIE_LIKED_3,
            UserRepositoryDataSource.FAKE_MOVIE_LIKED_4,))

        assert(movieRepository.getSavedMovies().size == 3)
        for(entity in movieRepository.getSavedMovies()) {
            assert(entity.isLiked)
        }
    }

    @Test
    fun sfds() = runBlocking {
    }
}