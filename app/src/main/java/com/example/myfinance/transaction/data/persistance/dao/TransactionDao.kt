package com.example.myfinance.transaction.data.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfinance.transaction.data.persistance.entity.TransactionEntity

@Dao
interface TransactionDao {
    @Query("SELECT * FROM `transaction`")
    fun readAll(): List<TransactionEntity>

    @Query("SELECT * FROM `transaction` WHERE account_id = :accountId")
    fun getAllByAccountId(accountId: Long): List<TransactionEntity>

    @Query("SELECT SUM(amount) " +
            "FROM `transaction` " +
            "WHERE account_id = :accountId")
    fun getSumAmountByAccountId(accountId: Long): Long

    @Insert
    fun insert(transaction: TransactionEntity): Long
}