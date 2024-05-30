package com.example.myfinance.transactions.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.presentation.LabeledTransactionList
import com.example.myfinance.transaction.presentation.components.TransactionSelectorDropDown
import com.example.myfinance.transaction.presentation.components.TransactionSelectorDropDownItem
import com.example.myfinance.transaction.presentation.components.TransactionTypeFormatter
import java.time.format.DateTimeFormatter

enum class TransactionKeySelector {
    TYPE,
    DATE
}

@Composable
fun TransactionsScreen(
    state: TransactionsState
) {
    if(state.selectedAccount == null) return
    val transactions = state.selectedAccount.transactions
    val options = listOf(
        TransactionSelectorDropDownItem("Type", TransactionKeySelector.TYPE),
        TransactionSelectorDropDownItem("Date", TransactionKeySelector.DATE)
    )

    var map: Map<String, List<Transaction>> by remember { mutableStateOf(mapOf()) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TransactionSelectorDropDown(
            options = options,
            onSelect = { selector ->
               map = when(selector){
                   TransactionKeySelector.DATE -> transactions
                       .groupBy { it.date }
                       .mapKeys { state.dateFormatter.format(it.key) }

                   TransactionKeySelector.TYPE -> transactions
                       .groupBy { it.type }
                       .mapKeys { it.key.description }
               }
            }
        )

        LabeledTransactionList(
            transactionsMap = map,
            dateTimeFormatter = state.dateFormatter,
            currencyAmountFormatter = state.currencyFormatter,
            modifier = Modifier.fillMaxHeight()
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
            state = state
        )
    }
}