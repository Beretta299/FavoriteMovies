package com.karas.movies.rest.data

interface RestManager {
    suspend fun loadNewDataPortion(pageNumber: Int)
}