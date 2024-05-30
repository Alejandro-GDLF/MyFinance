package com.example.myfinance.transactions.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.home.presentation.HomeState
import com.example.myfinance.transaction.presentation.LabeledTransactionList
import com.example.myfinance.transaction.presentation.components.TransactionTypeFormatter
import java.time.format.DateTimeFormatter

@Composable
fun TransactionsScreen(state: TransactionsState,
                       navController: NavHostController) {
    if(state.account == null) return
    val transactions = state.account!!.transactions
    val map = transactions.groupBy { it.type }

    Column {
        LabeledTransactionList(
            transactionsMap = map,
            formatter = TransactionTypeFormatter()
        )
    }
}

@Preview
@Composable
fun TransactionScreenPreview() {
    val state = TransactionsState(
        accounts = listOf(
            PreviewPresets.account,
            PreviewPresets.account
        ),
        currencyFormatter = CurrencyAmountFormatter(),
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TransactionsScreen(
            state = state,
            rememberNavController()
        )
    }
}