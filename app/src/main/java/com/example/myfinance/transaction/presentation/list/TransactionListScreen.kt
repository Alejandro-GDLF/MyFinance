package com.example.myfinance.transaction.presentation.list

import android.icu.util.CurrencyAmount
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinance.transaction.domain.Transaction
import com.example.myfinance.transaction.domain.TransactionType
import com.example.myfinance.transaction.presentation.list.components.TransactionListItem
import java.time.LocalDateTime
import java.util.Currency

@Composable
fun TransactionList(transactions: List<Transaction>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(transactions) {transaction ->
            TransactionListItem(transaction)
        }
    }
}

@Preview
@Composable
fun TransactionListPreview() {
    val amount: Double = -324.32
    val transactions = listOf<Transaction>(
        Transaction(
            id = 21,
            type = TransactionType(32, "Type1"),
            amount = CurrencyAmount(amount, Currency.getInstance("EUR")),
            date = LocalDateTime.now(),
            description = "Some descriptiosdfwfwefwefwefwefn",
            total = CurrencyAmount(amount, android.icu.util.Currency.getInstance("EUR"))
        ),
        Transaction(
            id = 21,
            type = TransactionType(32, "Type1"),
            amount = CurrencyAmount(amount, Currency.getInstance("EUR")),
            date = LocalDateTime.now(),
            description = "Some description",
            total = CurrencyAmount(amount, android.icu.util.Currency.getInstance("EUR"))
        ),
        Transaction(
            id = 21,
            type = TransactionType(32, "Type1"),
            amount = CurrencyAmount(amount, Currency.getInstance("EUR")),
            date = LocalDateTime.now(),
            description = "Some description",
            total = CurrencyAmount(amount, android.icu.util.Currency.getInstance("EUR"))
        )
    )

    Box(modifier = Modifier.width(360.dp)) {
        TransactionList(transactions)
    }
}