package com.fady.newsapp.presentation.favorites

import com.fady.newsapp.domain.models.NewsItem

sealed class FavoriteEvents {
    data class OnRemoveFromFavorite(val newsItem: NewsItem) : FavoriteEvents()
    data object OnRetry : FavoriteEvents()
    data object ShowError : FavoriteEvents()
}