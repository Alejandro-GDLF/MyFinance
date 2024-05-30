package com.example.myfinance.transaction.presentation.new_transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.Constants
import com.example.myfinance.core.presentation.DatePickerButton
import com.example.myfinance.core.presentation.DropDownAccount
import com.example.myfinance.core.presentation.DropDownTransactionType
import com.example.myfinance.transaction.domain.model.TransactionType
import com.example.myfinance.core.presentation.CenterAlignedTopAppBar
import java.time.LocalDateTime
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
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
    if (state.isCreated) {
        navHostController.popBackStack()
        return
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = "Create new transaction",
                onNavigationClick = { navHostController.navigateUp() },
                hasMenuIcon = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            var account by remember { mutableStateOf(state.accounts.firstOrNull()) }
            DropDownAccount(
                options = state.accounts,
                defaultAccount = account,
                onAccountSelected = { selectedAccount ->
                    account = selectedAccount
                }
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

            Row(
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
        onCreateTransaction = {}
    )
}
