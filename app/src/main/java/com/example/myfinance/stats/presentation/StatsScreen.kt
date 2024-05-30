package com.example.myfinance.stats.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.AppHeader
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.transaction.domain.model.Transaction
import java.time.format.DateTimeFormatter

@Composable
fun StatsScreen(
    state: StatsScreenState,
    navController: NavHostController
) {
    Scaffold(
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
        }
    ) { innerPadding ->

        if( state.account == null ) {
            return@Scaffold
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BalanceCard(
                state.balance,
                state.spent,
                state.income
            )

            LazyVerticalGrid(
                modifier = Modifier.padding(16.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ){
                items(state.typeAndTotal.toList()) { (type, total) ->
                    TransactionTypeTotal(transactionType = type, total = total)
                }
            }
        }
    }
}

@Composable
fun TransactionTypeTotal(
    transactionType: String,
    total: String
) {
    Card {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = transactionType,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = total,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Composable
fun BalanceCard(
    balance: String,
    spent: String,
    income: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Available Balance",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = balance,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF03DAC5), RoundedCornerShape(50))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "SPENT ${spent}",
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF018786), RoundedCornerShape(50))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "INCOME ${income}",
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StatsScreenPreview() {
    StatsScreen(
        state = StatsScreenState(
            account = PreviewPresets.account,
            isLoading = false,
            dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            currencyAmountFormatter = CurrencyAmountFormatter(),
            typeAndTotal = mapOf(
                "Something" to "40$",
                "Something more" to "50$"
            )
        ),
        navController = rememberNavController()
    )
}