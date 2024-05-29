package com.example.myfinance.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.presentation.AccountCard
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.AppHeader
import com.example.myfinance.core.presentation.DropDownAccount
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.transaction.presentation.time_labeled_list.TimeLabeledTransactionList
import java.time.format.DateTimeFormatter


@Composable
fun HomeScreen(
    state: HomeState,
    navController: NavHostController,
) {
    LaunchedEffect(state.accounts) {
        // Imprimir la lista de accounts por consola
        println("Accounts list: ${state.accounts}")
    }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
                return@Scaffold
            }

            var account by remember { mutableStateOf(state.selectedAccount) }

            DropDownAccount(
                options = state.accounts,
                defaultAccount = account,
                onAccountSelected = { selectedAccount ->
                    account = selectedAccount
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    navController.navigate("create_account")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add new account"
                )
            }
            if (account == null)return@Scaffold
            Spacer(modifier = Modifier.height(10.dp))

            AccountCard(
                account = account!!,
                currencyAmountFormatter = state.currencyFormatter
            )

            Spacer(modifier = Modifier.height(20.dp))

            TimeLabeledTransactionList(
                transactionsGrouped = account!!.transactions.groupBy { it.date },
                dateFormatter = state.dateFormatter
            )

            Spacer(modifier = Modifier.height(50.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val state = HomeState(
        accounts = listOf(
            PreviewPresets.account,
            PreviewPresets.account
        ),
        currencyFormatter = CurrencyAmountFormatter(),
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")
    )

    HomeScreen(state = state, rememberNavController())
}
