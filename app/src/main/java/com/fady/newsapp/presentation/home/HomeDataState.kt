package com.fady.newsapp.presentation.home


data class HomeDataState(
    val totalItems: Int = 0,
    val loading: Boolean = false,
    val error: String? = null
)
