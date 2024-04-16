package com.fady.newsapp.presentation.utils

import com.fady.newsapp.domain.models.NewsItem
import java.util.UUID

fun NewsItem?.generateItemId(): String {
    return this?.let {
        "${it.title}-${it.publishedAt}"
    } ?: UUID.randomUUID().toString()
}