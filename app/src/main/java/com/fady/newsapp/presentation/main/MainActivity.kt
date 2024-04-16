package com.fady.newsapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fady.newsapp.presentation.components.navgraph.AppGraph
import com.fady.newsapp.presentation.components.navgraph.Route
import com.fady.newsapp.presentation.components.navgraph.bottomNavigation.NewsBottomNavigation
import com.fady.newsapp.presentation.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var requestSplashRemoved: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            requestSplashRemoved == null
        }

        setContent {
            val navController = rememberNavController()
            val currentRoute = navController.currentBackStackEntryAsState().value
            val selectedIndex by remember(currentRoute?.destination?.route) {
                mutableIntStateOf(
                    when (currentRoute?.destination?.route) {
                        Route.HomeScreen.route -> 0
                        Route.FavoritesScreen.route -> 1
                        else -> 0
                    }
                )
            }
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    AnimatedVisibility(
                        visible = isMainScreen(currentRoute),
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        NewsBottomNavigation(navController, selectedIndex)
                    }
                }) {
                    AppGraph(startDestination = Route.HomeScreen,
                        navController = navController,
                        paddingValues = it,
                        onRequestSplashRemoved = {
                            requestSplashRemoved = true
                        })
                }
            }
        }
    }

    private fun isMainScreen(navBackStackEntry: NavBackStackEntry?): Boolean {
        return navBackStackEntry?.destination?.route == Route.HomeScreen.route || navBackStackEntry?.destination?.route == Route.FavoritesScreen.route
    }
}
