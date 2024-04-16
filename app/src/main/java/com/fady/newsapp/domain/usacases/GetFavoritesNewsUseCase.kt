package com.fady.newsapp.domain.usacases

import com.fady.newsapp.data.datasource.remote.Resource
import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavoritesNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(
    ): Flow<Resource<List<NewsItem>>> = flow {
        emit(newsRepository.getFavoriteNews())
    }.flowOn(Dispatchers.IO)
}
