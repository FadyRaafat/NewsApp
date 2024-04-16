package com.fady.newsapp.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fady.newsapp.presentation.components.FavoriteArticleItem
import com.fady.newsapp.presentation.theme.dimen_16
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FavoritesScreen(
    favoritesState: StateFlow<FavoritesState>,
    onEvent: (FavoriteEvents) -> Unit,
) {

    val lazyListState = rememberLazyGridState()
    val state = favoritesState.collectAsStateWithLifecycle().value
    when {
        state.loading -> {
        }

        state.newsItems.isNotEmpty() -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(dimen_16),
                horizontalArrangement = Arrangement.spacedBy(dimen_16),
                verticalArrangement = Arrangement.spacedBy(dimen_16),
                state = lazyListState
            ) {
                items(state.newsItems.size) { index ->
                    FavoriteArticleItem(
                        article = state.newsItems[index],
                        onRemoveFromFavorites = {
                            onEvent(FavoriteEvents.OnRemoveFromFavorite(it))
                        })
                }
            }

        }

        else -> {
        }
    }
}