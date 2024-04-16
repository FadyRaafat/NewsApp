package com.fady.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.domain.usacases.AddToFavoritesUseCase
import com.fady.newsapp.domain.usacases.GetRemoteNewsUseCase
import com.fady.newsapp.domain.usacases.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRemoteNewsUseCase: GetRemoteNewsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {


    private val _itemsState = MutableStateFlow(HomeDataState())
    val itemsState: StateFlow<HomeDataState> = _itemsState.asStateFlow()

    private val _state: MutableStateFlow<PagingData<NewsItem>> =
        MutableStateFlow(value = PagingData.empty())

    var state = _state.asStateFlow()
        private set

    init {
        getNews()
    }


    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.OnAddToFavorite -> {
                addToFavorite(event.newsItem)
            }

            is HomeEvents.OnRemoveFromFavorite -> {
                removeFromFavorite(event.newsItem)
            }

            is HomeEvents.OnRetry -> {
                getNews()
            }

            is HomeEvents.ShowError -> {
                _itemsState.update { it.copy(loading = false, error = "Error") }
            }
        }
    }

    private fun getNews() {
        getRemoteNewsUseCase().distinctUntilChanged().cachedIn(viewModelScope)
            .onEach { pagingData ->
                _state.value = pagingData
                _itemsState.update { it.copy(loading = false, error = "") }

            }.launchIn(viewModelScope)
    }

    private fun addToFavorite(newsItem: NewsItem) {
        addToFavoritesUseCase(newsItem)
    }

    private fun removeFromFavorite(newsItem: NewsItem) {
        removeFromFavoritesUseCase(newsItem)
    }


}