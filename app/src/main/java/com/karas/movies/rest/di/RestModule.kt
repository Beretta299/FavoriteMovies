package com.karas.movies.rest.di

import com.karas.movies.db.data.MovieManager
import com.karas.movies.db.data.MovieManagerImpl
import com.karas.movies.rest.data.RestManager
import com.karas.movies.rest.data.RestManagerImpl
import com.karas.movies.rest.data.RestRepository
import com.karas.movies.rest.data.RestRepositoryImpl
import com.karas.movies.rest.service.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiService.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRestRepository(apiService: ApiService): RestRepository {
        return RestRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRestManager(restRepository: RestRepository, movieManager: MovieManager) : RestManager{
        return RestManagerImpl(restRepository, movieManager)
    }
}