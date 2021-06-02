package com.karas.movies.pojo

data class MovieData(val id: Int? = null, val title: String, val description: String, val rank: Float, val imagePath: String, val releaseDate: String, var isLiked: Boolean)
