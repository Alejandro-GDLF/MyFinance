package com.example.myfinance.transaction.data.dao

import androidx.room.Dao
import com.example.myfinance.transaction.data.entity.TransactionTypeDbDto

@Dao
interface TransactionTypeDao {
    fun get(id: Long): TransactionTypeDbDto
}