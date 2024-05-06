package com.example.myfinance.domain.model

import android.icu.util.CurrencyAmount
import java.time.LocalDateTime

data class Account (
    var id: Long,
    var profileId: Long,
    var number: String,
    var balance: CurrencyAmount,
    var creationDate: LocalDateTime
)