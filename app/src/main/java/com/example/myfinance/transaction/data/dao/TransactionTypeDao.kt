package com.example.myfinance.transaction.data.dao

import androidx.room.Dao
import com.example.myfinance.transaction.data.entity.TransactionTypeEntity

@Dao
interface TransactionTypeDao {
    fun get(id: Long): TransactionTypeEntity
}