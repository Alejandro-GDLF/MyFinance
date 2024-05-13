package com.example.myfinance.transaction.data.dao

import androidx.room.Dao
import com.example.myfinance.transaction.data.entity.TransactionEntity

@Dao
interface TransactionDao {
    fun readAll(): List<TransactionEntity>

    fun getAccountId(id: Long): Long
}