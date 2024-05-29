package com.example.myfinance.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun AppScaffold(
    navController: NavHostController,
    content: @Composable() (BoxScope.()-> Unit)
) {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomNavigationBar(navController = navController)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content()
        }
    }
}