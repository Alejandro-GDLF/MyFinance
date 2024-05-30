package com.example.myfinance.core.presentation

import android.accounts.Account
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.myfinance.account.presentation.new_account.NewAccountScreen
import com.example.myfinance.account.presentation.new_account.NewAccountViewModel
import com.example.myfinance.home.presentation.HomeScreen
import com.example.myfinance.home.presentation.HomeViewModel
import com.example.myfinance.profile.presentation.new_profile.NewProfile
import com.example.myfinance.profile.presentation.new_profile.NewProfileViewModel
import com.example.myfinance.profile.presentation.profile_picker.ProfilePicker
import com.example.myfinance.profile.presentation.profile_picker.ProfilePickerViewModel
import com.example.myfinance.stats.presentation.StatsScreen
import com.example.myfinance.stats.presentation.StatsViewModel
import com.example.myfinance.transaction.presentation.new_transaction.NewTransaction
import com.example.myfinance.transaction.presentation.new_transaction.NewTransactionViewModel
import com.example.myfinance.transaction.presentation.new_transaction_type.NewTransactionType
import com.example.myfinance.transaction.presentation.new_transaction_type.NewTransactionTypeViewModel
import com.example.myfinance.transactions.presentation.TransactionsScreen
import com.example.myfinance.transactions.presentation.TransactionsViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        composable("auth") {
            val viewModel: ProfilePickerViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            ProfilePicker(
                state = state,
                selectProfile = viewModel::selectProfile,
                navController
            )
        }

        composable("home") {
            val viewModel: HomeViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            val navBackStack = navController.currentBackStackEntryAsState().value
            DisposableEffect(navBackStack) {
                val observer = LifecycleEventObserver {_, event ->
                    if( event == Lifecycle.Event.ON_RESUME){
                        viewModel.collectAccounts()

                    }
                }
                navBackStack?.lifecycle?.addObserver(observer)

                onDispose { navBackStack?.lifecycle?.removeObserver(observer) }
            }

            AppScaffold(navController = navController) {
                HomeScreen(
                    state = state,
                    navController = navController,
                    updateSelectedAccount = viewModel::updateSelectedAccount
                )
            }
        }

        composable("overview") {
            val viewModel: StatsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            StatsScreen(state = state, navController = navController)
        }

        composable("create_profile") {
            val viewModel: NewProfileViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            NewProfile(
                navController = navController,
                state = state,
                onCreate = viewModel::createNewProfile,
                updateName = viewModel::updateName,
                updateEmail = viewModel::updateEmail
            )
        }

        composable("create_account"){
            val viewModel: NewAccountViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            NewAccountScreen(
                state = state,
                createAccount = viewModel::createAccount,
                navHostController = navController,
                updateNumber = viewModel::updateNumber
            )
        }

        composable("create_transaction") {
            val viewModel: NewTransactionViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            val navBackStack = navController.currentBackStackEntryAsState().value
            DisposableEffect(navBackStack) {
                val observer = LifecycleEventObserver {_, event ->
                    if( event == Lifecycle.Event.ON_RESUME){
                        viewModel.refreshTransactionTypes()
                    }
                }
                navBackStack?.lifecycle?.addObserver(observer)

                onDispose { navBackStack?.lifecycle?.removeObserver(observer) }
            }

            NewTransaction(
                state = state,
                navHostController = navController,
                updateSelectedAccount = viewModel::updateSelectedAccount,
                updateDescription = viewModel::updateDescription,
                updateDate = viewModel::updateDate,
                updateAmount = viewModel::updateAmount,
                updateType = viewModel::updateType,
                onCreateTransaction = viewModel::onCreateTransaction
            )
        }

        composable("create_type") {
            val viewModel: NewTransactionTypeViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            NewTransactionType(
                state = state,
                navController = navController,
                updateDescription = viewModel::updateDescription,
                onCreate = viewModel::create
            )
        }
        composable("transactions-list") {
            val viewModel: TransactionsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            AppScaffold(navController = navController) {
                TransactionsScreen(
                    state = state,
                    navController
                )
            }
        }
    }
}
