package com.fady.newsapp.domain.usacases

import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(
        newsItem: NewsItem
    ) {
        return newsRepository.removeFromFavorite(newsItem)
    }
}
