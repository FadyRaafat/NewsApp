package com.fady.newsapp.presentation.home

import com.fady.newsapp.domain.models.NewsItem


sealed class HomeEvents {
    data class OnAddToFavorite(val newsItem: NewsItem) : HomeEvents()
    data class OnRemoveFromFavorite(val newsItem: NewsItem) : HomeEvents()
    data object OnRetry : HomeEvents()
    data object ShowError : HomeEvents()
}
