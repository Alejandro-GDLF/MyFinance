package com.example.myfinance.transaction.presentation.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.model.TransactionType
import com.example.myfinance.transaction.presentation.list.components.TransactionListItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency

@Composable
fun TransactionList(
    transactions: List<Transaction>,
    dateTimeFormatter: DateTimeFormatter,
    currencyAmountFormatter: CurrencyAmountFormatter
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),

    ) {
        items(transactions) {transaction ->
            TransactionListItem(
                transaction,
                dateTimeFormatter = dateTimeFormatter,
                currencyAmountFormatter = currencyAmountFormatter
            )
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
            amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
            date = LocalDateTime.now(),
            description = "Some descriptiosdfwfwefwefwefwefn",
            total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
        ),
        Transaction(
            id = 21,
            type = TransactionType(32, "Type1"),
            amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
            date = LocalDateTime.now(),
            description = "Some description",
            total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
        ),
        Transaction(
            id = 21,
            type = TransactionType(32, "Type1"),
            amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
            date = LocalDateTime.now(),
            description = "Some description",
            total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
        )
    )

    TransactionList(
        transactions,
        dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
        currencyAmountFormatter = CurrencyAmountFormatter()
    )
}