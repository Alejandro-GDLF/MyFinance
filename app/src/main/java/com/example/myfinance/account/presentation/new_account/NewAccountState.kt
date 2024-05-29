package com.example.myfinance.account.presentation.new_account

import java.time.LocalDateTime

data class NewAccountState (
    var number: String = "",
    val createDate: LocalDateTime = LocalDateTime.now()
)