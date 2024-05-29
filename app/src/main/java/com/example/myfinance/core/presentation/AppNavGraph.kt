package com.example.myfinance.core.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myfinance.account.presentation.new_account.NewAccountScreen
import com.example.myfinance.account.presentation.new_account.NewAccountViewModel
import com.example.myfinance.home.presentation.HomeScreen
import com.example.myfinance.home.presentation.HomeViewModel
import com.example.myfinance.profile.presentation.login.Login
import com.example.myfinance.profile.presentation.login.LoginViewModel
import com.example.myfinance.profile.presentation.new_profile.NewProfile
import com.example.myfinance.profile.presentation.new_profile.NewProfileViewModel
import com.example.myfinance.profile.presentation.profile_picker.ProfilePicker
import com.example.myfinance.profile.presentation.profile_picker.ProfilePickerViewModel

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

            HomeScreen(
                state = state,
                navController
            )
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
    }
}