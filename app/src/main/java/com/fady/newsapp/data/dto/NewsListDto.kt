package com.fady.newsapp.data.dto


import com.fady.newsapp.domain.models.NewsItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsListDto(
    @SerialName("articles") val articles: List<Article?>?,
    @SerialName("status") val status: String?,
    @SerialName("totalResults") val totalResults: Int?
) {
    @Serializable
    data class Article(
        @SerialName("author") val author: String?,
        @SerialName("content") val content: String?,
        @SerialName("description") val description: String?,
        @SerialName("publishedAt") val publishedAt: String?,
        @SerialName("title") val title: String?,
        @SerialName("url") val url: String?,
        @SerialName("urlToImage") val urlToImage: String?
    ) {
        fun toNewsItem() = NewsItem(
            author = author ?: "",
            description = description ?: "",
            publishedAt = publishedAt ?: "",
            title = title ?: "",
            imageUrl = urlToImage ?: "",
        )
    }

}