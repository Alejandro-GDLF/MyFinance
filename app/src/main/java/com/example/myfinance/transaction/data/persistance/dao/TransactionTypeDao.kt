package com.example.myfinance.transaction.data.persistance.dao

import androidx.room.Dao
import com.example.myfinance.transaction.data.persistance.entity.TransactionTypeEntity

@Dao
interface TransactionTypeDao {
    fun get(id: Long): TransactionTypeEntity
}