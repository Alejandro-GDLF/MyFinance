package com.example.myfinance.account.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NewAccountCard(
    onCreateAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .then(modifier),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Create account")
            Button(onClick = onCreateAccount) {
                Text(text = "Create")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewAccountCardPreview() {
    NewAccountCard (
        {}
    )
}