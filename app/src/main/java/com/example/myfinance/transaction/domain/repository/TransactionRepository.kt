package com.example.myfinance.transaction.domain.repository

import com.example.myfinance.transaction.domain.model.Transaction

interface TransactionRepository {
    suspend fun read(): List<Transaction>
}