package com.fady.newsapp.domain.models

data class NewsItem(
    val author: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val imageUrl: String,
)
