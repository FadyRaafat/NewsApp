package com.fady.newsapp.presentation.components.navgraph

sealed class Route(val route: String) {

    data object SplashScreen : Route("splash")
    data object HomeScreen : Route("home")
    data object FavoritesScreen : Route("favorites")
}
