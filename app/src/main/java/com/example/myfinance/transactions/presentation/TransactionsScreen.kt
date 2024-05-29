package com.example.myfinance.transactions.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.transaction.presentation.LabeledTransactionList
import com.example.myfinance.transaction.presentation.components.TransactionTypeFormatter

@Composable
fun TransactionsScreen(state: TransactionsState) {
    val transactions = state.account.transactions
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
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TransactionsScreen(
            TransactionsState(PreviewPresets.account)
        )
    }
}