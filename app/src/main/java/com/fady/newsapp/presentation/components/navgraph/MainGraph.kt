package com.fady.newsapp.presentation.components.navgraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fady.newsapp.presentation.favorites.FavoritesScreen
import com.fady.newsapp.presentation.favorites.FavoritesViewModel
import com.fady.newsapp.presentation.home.HomeScreen
import com.fady.newsapp.presentation.home.HomeViewModel

fun NavGraphBuilder.mainGraph(
) {
    composable(route = Route.HomeScreen.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        HomeScreen(homeViewModel.state,
            homeViewModel.itemsState,
            onEvent = { homeViewModel.onEvent(it) })
    }

    composable(route = Route.FavoritesScreen.route) {
        val favoritesViewModel: FavoritesViewModel = hiltViewModel()
        FavoritesScreen(
            favoritesState = favoritesViewModel.state,
            onEvent = { favoritesViewModel.onEvent(it) }
        )
    }

}
