package com.fady.newsapp.data.di

import com.fady.newsapp.data.datasource.remote.NewsRemoteDataSource
import com.fady.newsapp.data.repository.NewsRepositoryImpl
import com.fady.newsapp.data.service.ClientService
import com.fady.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource)
    }


    @Provides
    @Singleton
    fun provideProductServices(retrofit: Retrofit): ClientService =
        retrofit.create(ClientService::class.java)

}
