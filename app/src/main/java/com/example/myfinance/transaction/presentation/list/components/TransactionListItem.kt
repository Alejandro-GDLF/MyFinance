package com.example.myfinance.transaction.presentation.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.model.TransactionType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency

@Composable
fun TransactionListItem(
    transaction: Transaction,
    dateTimeFormatter: DateTimeFormatter,
    currencyAmountFormatter: CurrencyAmountFormatter
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BoldSmallColumnText(
                boldText = transaction.description,
                smallText = dateTimeFormatter.format(transaction.date),
                modifier = Modifier.weight(1f, fill = true)
            )

            Spacer(modifier = Modifier.width(10.dp))

            BoldSmallColumnText(
                boldText = currencyAmountFormatter.format(transaction.amount),
                smallText = currencyAmountFormatter.format(transaction.total),
                alignment = Alignment.End,
                modifier = Modifier.wrapContentWidth(Alignment.End)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionListItemPreview() {
    val amount: Double = -322342344.32
    MaterialTheme {
        TransactionListItem(
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some descriptionsdfsd fwwcfwevf ewecvefv wefwe fwedc",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            currencyAmountFormatter = CurrencyAmountFormatter()
        )
    }
}