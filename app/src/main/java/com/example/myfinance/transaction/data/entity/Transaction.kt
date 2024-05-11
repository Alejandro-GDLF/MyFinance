package com.example.myfinance.transaction.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myfinance.account.data.entity.Account

@Entity(tableName = "transaction",
        foreignKeys = [
            ForeignKey(entity = TransactionType::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("type_id"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
            ),
            ForeignKey( entity = Account::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("account_id"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
            )
        ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "type_id", index = true)
    val typeId: Long,
    @ColumnInfo(name = "account_id", index = true)
    val accountId: Long,
    val amount: Long,
    val total: Long,
    @ColumnInfo(name = "currency_code")
    val currencyCode: String, // ISO 4217 currency code
    val date: Long, // UNIX timestamp
    val description: String
)
