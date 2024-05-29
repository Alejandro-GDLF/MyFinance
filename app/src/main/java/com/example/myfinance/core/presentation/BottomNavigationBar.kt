package com.example.myfinance.core.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items: List<NavigationItem> = listOf(
        NavigationItem.Transactions,
        NavigationItem.Home,
        NavigationItem.Overview
    )

    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItem.Home.route) }

    items.forEachIndexed{ index, nav ->
        if( nav.route == currentRoute )
            selectedItem = index
    }

    NavigationBar {
        items.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = selectedItem == index,
                icon = { Icon(navigationItem.icon, contentDescription = navigationItem.title) },
                //label = { Text(text = navigationItem.title) },
                alwaysShowLabel = false,
                onClick = {
                    selectedItem = index
                    currentRoute = navigationItem.route
                    navController.navigate(navigationItem.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}