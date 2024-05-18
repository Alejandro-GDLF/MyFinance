package com.example.myfinance.account.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfinance.account.data.entity.AccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    fun getAll(): List<AccountEntity>

    @Query("SELECT * FROM account WHERE id = :id")
    fun get(id: Long): AccountEntity

    @Query("SELECT * FROM account WHERE profile_id = :profileId")
    fun getByProfile(profileId: Long): List<AccountEntity>

    @Insert
    fun insert(accountEntities: List<AccountEntity>): List<Long>

    @Insert
    fun insert(accountEntity: AccountEntity): Long
}