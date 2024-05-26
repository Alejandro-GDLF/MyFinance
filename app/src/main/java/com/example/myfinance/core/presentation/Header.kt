package com.example.myfinance.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinance.R

@Composable
fun AppHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1F))

        Image(
            painter = painterResource(id = R.drawable.my_finance_title),
            contentDescription = stringResource(id = R.string.my_finance_title),
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .weight(1F)
        )

        Spacer(modifier = Modifier.weight(1F))

        Icon(
            Icons.Rounded.AccountCircle,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppHeaderPreview() {
    AppHeader(modifier = Modifier
        .height(100.dp)
        .width(260.dp))
}