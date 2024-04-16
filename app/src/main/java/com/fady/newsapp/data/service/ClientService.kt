package com.fady.newsapp.data.service

import com.fady.newsapp.BuildConfig
import com.fady.newsapp.data.dto.NewsListDto
import com.fady.newsapp.data.utils.API_KEY
import com.fady.newsapp.data.utils.DOMAINS
import com.fady.newsapp.data.utils.PAGE
import com.fady.newsapp.data.utils.PAGE_SIZE
import com.fady.newsapp.data.utils.QueryValue
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientService {

    @GET("everything")
    suspend fun getNews(
        @Query(DOMAINS) domains: String = QueryValue.DOMAINS.value,
        @Query(API_KEY) apiKey: String = BuildConfig.API_KEY,
        @Query(PAGE_SIZE) pageSize: String = QueryValue.PAGE_SIZE.value,
        @Query(PAGE) page: String,
    ): NewsListDto
}