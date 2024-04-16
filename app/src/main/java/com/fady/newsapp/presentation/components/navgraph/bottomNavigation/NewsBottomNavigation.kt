package com.fady.newsapp.presentation.components.navgraph.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.fady.newsapp.R
import com.fady.newsapp.presentation.components.navgraph.Route

@Composable
fun NewsBottomNavigation(
    navController: NavController, selectedIndex: Int
) {
    BottomNavigationBar(selectedIndex = selectedIndex, items = listOf(
        BottomNavItem(
            stringResource(id = R.string.home),
            Route.HomeScreen,
            unSelectedIcon = painterResource(id = R.drawable.ic_home),
            selectedIcon = painterResource(id = R.drawable.ic_home_selected),

            ),
        BottomNavItem(
            stringResource(id = R.string.favorites),
            Route.FavoritesScreen,
            unSelectedIcon = painterResource(id = R.drawable.ic_like),
            selectedIcon = painterResource(id = R.drawable.ic_like_selected),
        ),
    ), onItemClick = {
        navController.navigate(it.route.route)
    })
}
