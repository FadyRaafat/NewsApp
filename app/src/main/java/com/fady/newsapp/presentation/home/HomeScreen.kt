package com.fady.newsapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.presentation.components.ArticleItem
import com.fady.newsapp.presentation.components.ArticleItemShimmer
import com.fady.newsapp.presentation.components.EmptyScreen
import com.fady.newsapp.presentation.utils.generateItemId
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    homeData: StateFlow<PagingData<NewsItem>>,
    state: StateFlow<HomeDataState>,
    onEvent: (HomeEvents) -> Unit,
) {

    val homeState = state.collectAsStateWithLifecycle().value
    val lazyListState = rememberLazyListState()
    val pagingData = homeData.collectAsLazyPagingItems()

    Scaffold {
        if (homeState.error.isNullOrBlank().not()) {
            EmptyScreen(
                modifier = Modifier.fillMaxSize(),
                onRetry = { onEvent(HomeEvents.OnRetry) })
        } else {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                pagingData.apply {
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            items(10) {
                                ArticleItemShimmer()
                            }
                        }

                        is LoadState.Error -> {
                            onEvent(HomeEvents.ShowError)
                        }

                        else -> {
                            items(pagingData.itemCount,
                                key = { index ->
                                    pagingData.peek(index).generateItemId()
                                }) { index ->
                                pagingData[index]?.let { article ->
                                    ArticleItem(newsItem = article,
                                        onAddToFavorites = { item -> onEvent(HomeEvents.OnAddToFavorite(item)) },
                                        onRemoveFromFavorites = { item ->
                                            onEvent(
                                                HomeEvents.OnRemoveFromFavorite(
                                                    item
                                                )
                                            )
                                        })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
