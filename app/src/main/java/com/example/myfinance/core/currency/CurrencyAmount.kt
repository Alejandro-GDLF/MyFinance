package com.example.myfinance.core.currency

import java.math.BigDecimal
import java.util.Currency

data class CurrencyAmount(
    val amount: BigDecimal,
    val currency: Currency
) {
    constructor( amount: Long, currencyCode: String ) : this(
        amount = amount.toBigDecimal(),
        currency = Currency.getInstance(currencyCode)
    )

    operator fun plus(other: CurrencyAmount): CurrencyAmount {
        return CurrencyAmount(
            this.amount + other.amount,
            this.currency
        )
    }

    operator fun minus(other: CurrencyAmount): CurrencyAmount {
        return CurrencyAmount(
            this.amount - other.amount,
            this.currency
        )
    }

    operator fun minusAssign(other: CurrencyAmount) {
        throw UnsupportedOperationException("CurrencyAmount is immutable")
    }

    operator fun plusAssign(other: CurrencyAmount) {
        throw UnsupportedOperationException("CurrencyAmount is immutable")
    }

    operator fun compareTo(compare: Int): Int {
        return this.amount.compareTo(compare.toBigDecimal())
    }
}
