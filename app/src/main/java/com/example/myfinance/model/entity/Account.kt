package com.example.myfinance.model.entity

import android.icu.util.CurrencyAmount
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(tableName = "account",
    foreignKeys = [
        ForeignKey(entity = Profile::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("profile_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "profile_id", index = true)
    val profileId: Long,
    val number: String,
    val balance: Long,
    // ISO 4217 currency code
    @ColumnInfo(name = "currency_code")
    val currencyCode: String,
    // UNIX time
    @ColumnInfo(name = "creation_date")
    val creationDate: Long
)
