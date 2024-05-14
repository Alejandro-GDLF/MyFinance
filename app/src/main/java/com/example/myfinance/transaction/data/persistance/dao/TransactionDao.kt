package com.example.myfinance.transaction.data.persistance.dao

import androidx.room.Dao
import com.example.myfinance.transaction.data.persistance.entity.TransactionEntity

@Dao
interface TransactionDao {
    fun readAll(): List<TransactionEntity>


}