package com.example.myfinance.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.sharp.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, val icon: ImageVector, var title: String) {
    object Home: NavigationItem("Home", Icons.Rounded.Home, "Home")
    object Transactions: NavigationItem("Transactions", Icons.AutoMirrored.Rounded.List, "Transactions")
    object Overview: NavigationItem("Overview", Icons.Rounded.Person, "Overview")
}