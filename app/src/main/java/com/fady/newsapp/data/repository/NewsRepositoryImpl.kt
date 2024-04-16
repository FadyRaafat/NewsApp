package com.fady.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fady.newsapp.data.datasource.local.LocalDataSource
import com.fady.newsapp.data.datasource.remote.NewsRemoteDataSource
import com.fady.newsapp.data.datasource.remote.Resource
import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
) : NewsRepository {

    override fun getNews(): Flow<PagingData<NewsItem>> {
        return Pager(config = PagingConfig(
            10,
            enablePlaceholders = true,
        ), pagingSourceFactory = {
            newsRemoteDataSource
        }).flow
    }

    override fun getFavoriteNews(): Resource<List<NewsItem>> {
        return if (LocalDataSource.favoriteNews.isNotEmpty()) {
            Resource.Success(LocalDataSource.favoriteNews)
        } else {
            Resource.Error("No favorite news found")
        }
    }

    override fun addToFavorite(newsItem: NewsItem) {
        LocalDataSource.addFavoriteNews(newsItem)
    }

    override fun removeFromFavorite(newsItem: NewsItem) {
        LocalDataSource.removeFavoriteNews(newsItem)
    }


}