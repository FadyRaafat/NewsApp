package com.fady.newsapp.presentation.favorites

import com.fady.newsapp.domain.models.NewsItem

data class FavoritesState(
    val newsItems: List<NewsItem> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)