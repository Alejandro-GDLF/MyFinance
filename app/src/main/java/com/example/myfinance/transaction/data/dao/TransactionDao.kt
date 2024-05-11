package com.example.myfinance.transaction.data.dao

import androidx.room.Dao
import com.example.myfinance.transaction.data.entity.TransactionDbDto

@Dao
interface TransactionDao {
    fun readAll(): List<TransactionDbDto>

    fun getAccountId(id: Long): Long
}