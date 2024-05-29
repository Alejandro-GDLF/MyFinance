package com.example.myfinance.transaction.domain

import com.example.myfinance.transaction.domain.model.TransactionType
import javax.inject.Inject

class TransactionTypeFactory @Inject constructor() {
    fun create(description: String): TransactionType {
        return TransactionType(
            id = null,
            description = description
        )
    }
}