package com.example.myfinance

import android.icu.util.CurrencyAmount
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
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
    val balance: CurrencyAmount,
    val createDate: LocalDateTime
)
