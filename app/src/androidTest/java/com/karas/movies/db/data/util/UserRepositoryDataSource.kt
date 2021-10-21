package com.karas.movies.db.data.util

import com.karas.movies.db.model.MovieEntity

object UserRepositoryDataSource {
    val FAKE_MOVIE_1 = MovieEntity(null, "Mo1", "Me", 0F, "Im1", "", false)
    val FAKE_MOVIE_LIKED_2 = MovieEntity(null, "Mo2", "Me", 0F, "Im2", "", true)
    val FAKE_MOVIE_LIKED_3 = MovieEntity(null, "Mo3", "Me", 0F, "Im3", "", true)
    val FAKE_MOVIE_LIKED_4 = MovieEntity(null, "Mo4", "Me", 0F, "Im4", "", true)
}