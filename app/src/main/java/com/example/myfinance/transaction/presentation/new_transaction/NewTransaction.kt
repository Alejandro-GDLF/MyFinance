package com.example.myfinance.transaction.presentation.new_transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.Constants
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.core.presentation.DatePickerButton
import com.example.myfinance.core.presentation.DropDownAccount
import com.example.myfinance.core.presentation.DropDownTransactionType
import com.example.myfinance.transaction.domain.model.TransactionType
import java.time.LocalDateTime
import java.util.Date

@Composable
fun NewTransaction(
    state: NewTransactionState,
    navHostController: NavHostController,
    updateSelectedAccount: (Account) -> Unit,
    updateDescription: (String) -> Unit,
    updateDate: (Date) -> Unit,
    updateAmount: (String) -> Unit,
    updateType: (TransactionType) -> Unit,
    onCreateTransaction: () -> Unit
) {
    if (state.isCreated ) {
        navHostController.popBackStack("home", false)
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create new transaction",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        DropDownAccount(
            options = state.accounts,
            onSelectedAccount = updateSelectedAccount
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.description ?: "",
            onValueChange = updateDescription,
            label = { Text(text = "Description") }
        )

        DatePickerButton(
            onDatePicked = updateDate
        )

        Row (
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = state.amount ?: "",
                onValueChange = updateAmount,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = "EUR",
                onValueChange = {},
                readOnly = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                keyboardActions = KeyboardActions()
            )
        }

        DropDownTransactionType(
            options = state.transactionType,
            onSelected = updateType
        )

        Button(
            onClick = { navHostController.navigate("create_type") }) {
            Text(text = "Create new type")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onCreateTransaction
        ) {
            Text(text = "Create transaction")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewTransactionPreview() {
    NewTransaction(
        state = NewTransactionState(
            Constants.DEPOSIT_TRANSACTION_TYPE,
            amount = "",
            date = LocalDateTime.now(),
            description = "",
            accounts = listOf(),
            selectedAccount = null
        ),
        navHostController = rememberNavController(),
        updateSelectedAccount = {},
        updateDescription = {},
        updateDate = {},
        updateAmount = {},
        updateType = {},
        onCreateTransaction = { }
    )
}