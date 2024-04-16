package com.fady.newsapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fady.newsapp.data.datasource.remote.Resource
import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.domain.usacases.GetFavoritesNewsUseCase
import com.fady.newsapp.domain.usacases.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesNewsUseCase: GetFavoritesNewsUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase

) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state

    init {
        getFavorites()
    }

    private fun getFavorites() {
        getFavoritesNewsUseCase().onStart {
            _state.update { it.copy(loading = true) }
        }.onEach { resource ->
            when (resource) {
                is Resource.Success -> _state.update {
                    it.copy(
                        newsItems = resource.value, loading = false
                    )
                }

                else -> {}

            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: FavoriteEvents) {
        when (event) {
            is FavoriteEvents.OnRemoveFromFavorite -> {
                removeFromFavorite(event.newsItem)
                getFavorites()
            }

            is FavoriteEvents.OnRetry -> {
                getFavorites()
            }

            is FavoriteEvents.ShowError -> {
            }
        }
    }

    private fun removeFromFavorite(newsItem: NewsItem) {
        removeFromFavoritesUseCase(newsItem)
    }


}

