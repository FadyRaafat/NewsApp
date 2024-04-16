package com.fady.newsapp.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fady.newsapp.data.service.ClientService
import com.fady.newsapp.domain.models.NewsItem
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val apiService: ClientService
) : PagingSource<Int, NewsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        val page = params.key ?: 1
        val response = safeApiCall { apiService.getNews(page = page.toString()) }
        return when (response) {
            is Resource.Success -> {
                LoadResult.Page(data = response.value.articles?.mapNotNull { it?.toNewsItem() }
                    ?: emptyList(),
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (response.value.articles.isNullOrEmpty()) null else page.plus(1))
            }

            is Resource.Error -> {
                LoadResult.Error(Exception(response.error))
            }

            else -> {
                LoadResult.Error(Exception("Unknown Error"))
            }
        }

    }


    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
