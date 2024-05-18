package com.example.myfinance.account.domain

import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.domain.model.Transaction
import java.time.LocalDateTime

/**
 * @brief Entity class representin an Account
 */
data class Account (
    val id: Long?,
    val number: String,
    private var _balance: CurrencyAmount,
    val creationDate: LocalDateTime,
    private val _transactions: MutableList<Transaction> = mutableListOf()
) {
    val balance: CurrencyAmount
        get() = _balance

    val transactions: List<Transaction>
        get() = _transactions.toList()

    fun addTransaction(transaction: Transaction): Boolean {
        // Would check, if constrained, the transaction addition

        this._transactions.add(transaction)
        return true
    }

    override fun equals(other: Any?): Boolean {
        if( other?.javaClass != null || other?.javaClass != javaClass)
            return false

        return this.id == (other as Account).id
    }

    override fun hashCode(): Int {
        return this.id?.toInt() ?: 0
    }
}