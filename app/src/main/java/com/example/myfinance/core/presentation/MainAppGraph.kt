package com.example.myfinance.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myfinance.home.presentation.HomeScreen
import com.example.myfinance.home.presentation.HomeViewModel
import com.example.myfinance.stats.presentation.StatsScreen
import com.example.myfinance.stats.presentation.StatsViewModel
import com.example.myfinance.transactions.presentation.TransactionsScreen
import com.example.myfinance.transactions.presentation.TransactionsViewModel

@Composable
fun MainAppGraph(mainNavController: NavHostController, appNavHostController: NavHostController) {
    NavHost(
        navController = mainNavController,
        startDestination = "home"
    ) {
        composable("home") {
            val viewModel: HomeViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            val navBackStack by mainNavController.currentBackStackEntryAsState()
            DisposableEffect(navBackStack) {
                val observer = LifecycleEventObserver {_, event ->
                    if( event == Lifecycle.Event.ON_RESUME){
                        viewModel.collectAccounts()

                    }
                }
                navBackStack?.lifecycle?.addObserver(observer)

                onDispose { navBackStack?.lifecycle?.removeObserver(observer) }
            }

            HomeScreen(
                state = state,
                navController = appNavHostController,
                updateSelectedAccount = viewModel::updateSelectedAccount
            )
        }

        composable("overview") {
            val viewModel: StatsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            StatsScreen(state = state)
        }

        composable("transactions-list") {
            val viewModel: TransactionsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            TransactionsScreen(state = state)
        }
    }
}