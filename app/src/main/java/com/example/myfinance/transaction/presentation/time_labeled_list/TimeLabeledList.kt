package com.example.myfinance.transaction.presentation.time_labeled_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.LabelHeader
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.presentation.list.components.TransactionListItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TimeLabeledTransactionList(
    transactionsGrouped: Map<LocalDateTime, List<Transaction>>,
    dateFormatter: DateTimeFormatter
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        transactionsGrouped.forEach { (date, transactions) ->
            item {
                LabelHeader(text = dateFormatter.format(date))
            }

            items(transactions) {transaction ->
                TransactionListItem(
                    transaction = transaction,
                    dateTimeFormatter = DateTimeFormatter.ISO_DATE,
                    currencyAmountFormatter = CurrencyAmountFormatter()
                )
            }
        }
    }
}

@Preview
@Composable
fun TimeLabeledTransactionListPreview() {
    TimeLabeledTransactionList(
        PreviewPresets.account.transactions.groupBy { it.date },
        DateTimeFormatter.ofPattern("dd/MM/yy")
    )
}