package com.example.myfinance.account.domain

import java.time.LocalDateTime
import javax.inject.Inject

class AccountFactory @Inject constructor() {
    fun create(number: String, instant: LocalDateTime): Account {
        return Account(
            id = null,
            number = number,
            creationDate = instant
        )
    }
}