package com.fady.newsapp.data.datasource.local

import android.content.SharedPreferences
import com.fady.newsapp.domain.models.NewsItem
import com.google.gson.Gson

object LocalDataSource {

    const val FILE_PREFERENCES = "newsapp_prefernce"
    val gson = Gson()
    private var preferences: SharedPreferences? = null

    fun initialize(preferences: SharedPreferences?) {
        LocalDataSource.preferences = preferences
    }

    var favoriteNews: List<NewsItem>
        get() = preferences.processStoredObject(SharedPreferencesKeys.FAVORITE_NEWS.value) ?: emptyList()
        set(value) = preferences.storeObject(SharedPreferencesKeys.FAVORITE_NEWS.value, value)


    fun addFavoriteNews(newsItem: NewsItem) {
        val list = favoriteNews.toMutableList()
        list.add(newsItem)
        favoriteNews = list
    }

    fun removeFavoriteNews(newsItem: NewsItem) {
        val list = favoriteNews.toMutableList()
        list.remove(newsItem)
        favoriteNews = list
    }


}

