package com.example.myfinance.transaction.data.persistance.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_type")
data class TransactionTypeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val description: String
)
