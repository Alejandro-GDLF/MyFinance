package com.example.myfinance.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinance.account.presentation.AccountCard
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.AppHeader
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.transaction.presentation.list.TransactionList
import com.example.myfinance.transaction.presentation.time_labeled_list.TimeLabeledTransactionList
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    state: HomeState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AppHeader(modifier = Modifier.height(20.dp))
        
        Spacer(modifier = Modifier.height(10.dp))

        if( state.account == null )
            return

        AccountCard(
            account = state.account!!,
            currencyAmountFormatter = state.currencyFormatter
        )
        Spacer(modifier = Modifier.height(20.dp))

        TimeLabeledTransactionList(
            transactionsGrouped = state.account!!.transactions.groupBy { it.date }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val state = HomeState(
        account = PreviewPresets.account,
        currencyFormatter = CurrencyAmountFormatter(),
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")
    )
    
    HomeScreen(state = state)
}