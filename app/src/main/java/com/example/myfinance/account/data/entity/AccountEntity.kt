package com.example.myfinance.account.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myfinance.profile.data.dto.Profile

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
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "profile_id", index = true)
    val profileId: Long,
    val number: String,
    // UNIX time
    @ColumnInfo(name = "creation_date")
    val creationDate: Long
)
