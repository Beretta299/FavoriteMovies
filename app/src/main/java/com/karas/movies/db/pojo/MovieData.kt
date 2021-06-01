package com.karas.movies.db.pojo

import com.karas.movies.db.model.MovieEntity

data class MovieData(val id: Int? = null, val title: String, val description: String, val rank: Float, val imagePath: String)
