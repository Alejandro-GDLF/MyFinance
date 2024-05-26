package com.example.myfinance.account.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp

@Composable
fun TopSmallBottomStrong(
    topText: String,
    bottomText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = topText,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = bottomText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}