package com.example.myfinance.profile.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfinance.profile.data.dto.ProfileEntity

@Dao
interface ProfileDao {
    @Query("SELECT * FROM `profile`")
    fun getAll(): List<ProfileEntity>

    @Query("SELECT * FROM `profile` WHERE email = :email")
    fun get(email:String): ProfileEntity?

    @Query("SELECT * FROM `profile` WHERE id = :id")
    fun get(id: Long): ProfileEntity?

    @Insert
    fun insert(profile: ProfileEntity): Long
}