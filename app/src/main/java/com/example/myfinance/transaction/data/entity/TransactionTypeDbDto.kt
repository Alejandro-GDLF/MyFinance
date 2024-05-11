package com.example.myfinance.transaction.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_type")
data class TransactionTypeDbDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val description: String
)
