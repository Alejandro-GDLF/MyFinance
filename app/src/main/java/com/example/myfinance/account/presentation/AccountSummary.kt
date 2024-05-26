package com.example.myfinance.account.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.presentation.components.TopSmallBottomStrong
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.PreviewPresets

@Composable
fun AccountSummary(
    account: Account,
    currencyFormatter: CurrencyAmountFormatter
) {
    Card {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val income = account.transactions.stream().filter { it.amount > 0 }
                .map { it.amount }
                .reduce(CurrencyAmount(0L, "EUR"))
                    { amount, currencyAmount2 -> amount + currencyAmount2 }

            TopSmallBottomStrong(
                topText = "Ingresos",
                bottomText = currencyFormatter.format(income),
                modifier = Modifier.weight(1F)
            )

            Spacer(modifier = Modifier.width(20.dp))

            val expenses = account.transactions.stream().filter { it.amount < 0 }
                .map { it.amount }
                .reduce(CurrencyAmount(0L, "EUR"))
                    { amount, currencyAmount2 -> amount + currencyAmount2 }

            TopSmallBottomStrong(
                topText = "Gastos",
                bottomText = currencyFormatter.format(expenses),
                modifier = Modifier.weight(1F)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountSummaryPreview() {
    AccountSummary(
        account = PreviewPresets.account,
        currencyFormatter = CurrencyAmountFormatter()
    )
}