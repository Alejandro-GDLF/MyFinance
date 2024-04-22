package com.example.myfinance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val email: String
)