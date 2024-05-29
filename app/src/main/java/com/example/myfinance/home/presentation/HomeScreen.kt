package com.example.myfinance.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.presentation.AccountCard
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.presentation.AppHeader
import com.example.myfinance.core.presentation.DropDownAccount
import com.example.myfinance.core.presentation.PreviewPresets
import com.example.myfinance.transaction.presentation.time_labeled_list.TimeLabeledTransactionList
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    state: HomeState,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AppHeader(modifier = Modifier.height(20.dp))
        
        Spacer(modifier = Modifier.height(10.dp))

        if( state.isLoading ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
            return
        }

        var account: Account? by remember{ mutableStateOf(null) }
        DropDownAccount(
            options = state.accounts,
            onSelectedAccount = { account = it }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                navController.navigate("create_account")
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add new account"
            )
        }

        if( account == null ) return

        Spacer(modifier = Modifier.height(10.dp))

        AccountCard(
            account = account!!,
            currencyAmountFormatter = state.currencyFormatter
        )

        Spacer(modifier = Modifier.height(20.dp))

        TimeLabeledTransactionList(
            transactionsGrouped = account!!.transactions.groupBy { it.date },
            dateFormatter = state.dateFormatter
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val state = HomeState(
        accounts = listOf(
            PreviewPresets.account,
            PreviewPresets.account
        ),
        currencyFormatter = CurrencyAmountFormatter(),
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")
    )
    
    HomeScreen(state = state, rememberNavController())
}