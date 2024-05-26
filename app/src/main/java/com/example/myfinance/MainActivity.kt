package com.example.myfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.home.presentation.HomeScreen
import com.example.myfinance.home.presentation.HomeState
import com.example.myfinance.transaction.presentation.list.components.TransactionListItemPreview
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(background = Color.White),
            ) {
                Scaffold(
                    bottomBar = {
                        var tabIndex by remember { mutableStateOf(0) }
                        val tabs = listOf(
                            "Home", "Transactions", "Overview"
                        )
                        NavigationBar {
                            tabs.forEachIndexed { index, s ->
                                NavigationBarItem(
                                    label = { Text(text = s) },
                                    alwaysShowLabel = true,
                                    selected = tabIndex == index,
                                    onClick = { tabIndex = index },
                                    icon = {
                                        Icon(
                                            Icons.Filled.Home,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) {
                    Box(
                        modifier = Modifier.padding(
                            0.dp, 0.dp, 0.dp,
                            it.calculateBottomPadding()
                        )
                    ) {
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