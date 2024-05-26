package com.example.myfinance.account.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun AccountCard(
    account: Account,
    currencyAmountFormatter: CurrencyAmountFormatter,
    modifier: Modifier = Modifier
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .then(modifier)
        ) {
            Text(
                text = account.number,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = currencyAmountFormatter.format(account.balance),
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Preview
@Composable
fun AccountCardPreview() {
    val account = PreviewPresets.account
    
    AccountCard(account = account, CurrencyAmountFormatter())
}