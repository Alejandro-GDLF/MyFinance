package com.example.myfinance.account.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.transaction.presentation.list.TransactionList
import java.time.format.DateTimeFormatter

@Composable
fun AccountLastTransactions(
    account: Account,
    currencyAmountFormatter: CurrencyAmountFormatter,
    dateFormatter: DateTimeFormatter,
    nTransactions: Int = 3
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .aspectRatio(1.5F / 1F)
        ) {
            Text(
                text = account.number,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = currencyAmountFormatter.format(account.balance),
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(15.dp))

            if( account.transactions.size > nTransactions)
                TransactionList(
                    transactions = account.transactions.subList(0, 4),
                    dateTimeFormatter = dateFormatter,
                    currencyAmountFormatter = currencyAmountFormatter
                )
            else
                TransactionList(
                    transactions = account.transactions,
                    dateTimeFormatter = dateFormatter,
                    currencyAmountFormatter = currencyAmountFormatter
                )
        }
    }
}

@Preview
@Composable
fun AccountLastTransactionsPreview () {
    val account = PreviewPresets.account

    AccountLastTransactions(account, CurrencyAmountFormatter(), DateTimeFormatter.ISO_DATE_TIME)
}