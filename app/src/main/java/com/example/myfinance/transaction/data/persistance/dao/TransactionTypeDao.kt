package com.example.myfinance.transaction.data.persistance.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myfinance.transaction.data.persistance.entity.TransactionTypeEntity

@Dao
interface TransactionTypeDao {

    @Query("SELECT * FROM `transaction_type`")
    fun getAll(): List<TransactionTypeEntity>

    @Query("SELECT * FROM `transaction_type` WHERE id = :id")
    fun get(id: Long): TransactionTypeEntity
}