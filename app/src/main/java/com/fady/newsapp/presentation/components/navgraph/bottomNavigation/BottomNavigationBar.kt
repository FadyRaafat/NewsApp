package com.fady.newsapp.presentation.components.navgraph.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fady.newsapp.presentation.theme.dimen_12
import com.fady.newsapp.presentation.theme.dimen_16

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    onItemClick: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = modifier, backgroundColor = Color.White, elevation = dimen_12
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index

            BottomNavigationItem(selected = selectedIndex == index, onClick = {
                onItemClick(item)
            }, selectedContentColor = Black, unselectedContentColor = Gray, icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val iconPainter = if (isSelected) item.selectedIcon else item.unSelectedIcon
                    if (item.badgeCount > 0) {
                        androidx.compose.material.BadgedBox(badge = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(dimen_16)
                                    .background(Color.Red, shape = CircleShape)
                            ) {
                                Text(
                                    text = item.badgeCount.toString(),
                                    color = Color.White,
                                )
                            }
                        }) {
                            androidx.compose.material.Icon(
                                modifier = Modifier.size(25.dp),
                                painter = iconPainter,
                                contentDescription = item.name,
                                tint = Unspecified
                            )
                        }
                    } else {
                        androidx.compose.material.Icon(
                            modifier = Modifier.size(25.dp),
                            painter = iconPainter,
                            contentDescription = item.name,
                            tint = Unspecified
                        )
                    }
                    Text(
                        text = item.name,
                        textAlign = TextAlign.Center,
                        color = Black,
                        fontSize = 12.sp,
                    )
                }
            })
        }
    }
}
