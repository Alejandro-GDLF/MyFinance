package com.example.myfinance.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    // ISO 4217 currency code
    @ColumnInfo(name = "currency_code")
    val currencyCode: String,
    // UNIX time
    @ColumnInfo(name = "creation_date")
    val creationDate: Long
)
