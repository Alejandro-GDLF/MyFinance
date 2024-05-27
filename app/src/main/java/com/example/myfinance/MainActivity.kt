package com.example.myfinance

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.BottomNavigationBar
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.home.presentation.HomeScreen
import com.example.myfinance.home.presentation.HomeState
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(background = Color.White),
            ) {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(it)
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {

                                HomeScreen(
                                    state = HomeState(
                                        PreviewPresets.account,
                                        DateTimeFormatter.ISO_DATE,
                                        CurrencyAmountFormatter()
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}