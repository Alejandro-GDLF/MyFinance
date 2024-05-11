package com.example.myfinance.transaction.presentation.list.components

import android.icu.util.CurrencyAmount
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
import com.example.myfinance.transaction.domain.Transaction
import com.example.myfinance.transaction.domain.TransactionType
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Locale

@Composable
fun TransactionListItem(transaction: Transaction) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
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
                smallText = transaction.date.format(dateFormatter),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(10.dp))

            BoldSmallColumnText(
                boldText = formatCurrencyAmount(transaction.amount),
                smallText = formatCurrencyAmount(transaction.total),
                alignment = Alignment.End,
                modifier = Modifier.wrapContentWidth(Alignment.End)
            )
        }
    }
}

fun formatCurrencyAmount(amount: CurrencyAmount, locale: Locale = Locale.getDefault()): String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(amount.number)
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
                amount = CurrencyAmount(amount, Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some descriptionsdfsd fwwcfwevf ewecvefv wefwe fwedc",
                total = CurrencyAmount(amount, android.icu.util.Currency.getInstance("EUR"))
            )
        )
    }
}