package com.fady.newsapp.domain.repository

import androidx.paging.PagingData
import com.fady.newsapp.data.datasource.remote.Resource
import com.fady.newsapp.domain.models.NewsItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<PagingData<NewsItem>>

    fun getFavoriteNews(): Resource<List<NewsItem>>

    fun addToFavorite(newsItem: NewsItem)

    fun removeFromFavorite(newsItem: NewsItem)
}