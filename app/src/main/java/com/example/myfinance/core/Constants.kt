package com.example.myfinance.core

import com.example.myfinance.transaction.domain.model.TransactionType

object Constants {
    val DEPOSIT_TRANSACTION_TYPE = TransactionType(
        id = 1,
        description = "Deposit"
    )

    val WITHDRAW_TRANSACTION_TYPE = TransactionType(
        id = 2,
        description = "Withdraw"
    )

    val PREDEFINED_TRANSACTION_TYPES = listOf(
        DEPOSIT_TRANSACTION_TYPE,
        WITHDRAW_TRANSACTION_TYPE
    )
}