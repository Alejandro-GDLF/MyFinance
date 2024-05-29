package com.example.myfinance.transaction.presentation.components

import com.example.myfinance.core.Formatter
import com.example.myfinance.transaction.domain.model.TransactionType

class TransactionTypeFormatter: Formatter<TransactionType> {
    override fun format(obj: TransactionType): String {
        return obj.description
    }
}