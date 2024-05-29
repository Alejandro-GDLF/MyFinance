package com.example.myfinance.transaction.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.myfinance.core.Formatter
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.LabelHeader
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.presentation.list.components.TransactionListItem
import java.time.format.DateTimeFormatter

@Composable
fun <T> LabeledTransactionList(
    transactionsMap: Map<T, List<Transaction>>,
    formatter: Formatter<T>
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        transactionsMap.forEach { (key, transactions) ->
            item {
                LabelHeader(text = formatter.format(key))
            }

            items(transactions) {transaction ->
                TransactionListItem(
                    transaction = transaction,
                    dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                    currencyAmountFormatter = CurrencyAmountFormatter()
                )
            }
        }
    }
}