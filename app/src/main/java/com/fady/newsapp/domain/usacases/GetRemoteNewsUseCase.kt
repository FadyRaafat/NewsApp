package com.fady.newsapp.domain.usacases

import androidx.paging.PagingData
import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(
    ): Flow<PagingData<NewsItem>> {
        return newsRepository.getNews()
    }
}
