package com.example.myfinance.account.data.mapper

import com.example.myfinance.account.data.entity.AccountEntity
import com.example.myfinance.account.domain.Account
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.transaction.domain.model.Transaction
import java.time.LocalDateTime
import java.time.ZoneOffset

object RoomAccountMapper {
    fun toDomain(account: AccountEntity, transactions: List<Transaction>) = Account(
        id = account.id,
        number = account.number,
        creationDate = LocalDateTime.ofEpochSecond(account.creationDate, 0, ZoneOffset.UTC),
        _transactions = transactions.toMutableList()
    )

    fun toPersistance(account: Account, profile: Profile) = AccountEntity(
        id = account.id ?: 0,
        profileId = profile.id ?: 0,
        number = account.number,
        creationDate = account.creationDate.toEpochSecond(ZoneOffset.UTC)
    )
}