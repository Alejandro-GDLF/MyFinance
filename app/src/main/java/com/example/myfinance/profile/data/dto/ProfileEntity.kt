package com.example.myfinance.profile.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val email: String
)