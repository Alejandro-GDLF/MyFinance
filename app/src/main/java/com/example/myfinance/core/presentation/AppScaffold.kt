package com.example.myfinance.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AppScaffold(
    navController: NavHostController
) {
    val mainNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomNavigationBar(navController = mainNavController)
            }
        },
        topBar = {
            AppHeader(
                onUserClick = {},
                onLogoutClick = {
                    navController.navigate("auth") {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { AddTransactionFloatingButton(navController = navController) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MainAppGraph(mainNavController= mainNavController, appNavHostController = navController)
        }
    }
}