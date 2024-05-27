package com.example.myfinance.core.presentation

import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.model.TransactionType
import java.time.LocalDateTime
import java.time.Month
import java.util.Currency

object PreviewPresets {
    val amount = 23424L
    val account = Account(
        id = 1234L,
        number = "RET34",
        creationDate = LocalDateTime.now(),
        _transactions = mutableListOf<Transaction>(
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some descriptiosdfwfwefwefwefwefn",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some description",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some description",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.of(2002, Month.AUGUST, 12, 1, 1),
                description = "Some description",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.of(2002, Month.AUGUST, 12, 1, 1),
                description = "Some description",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.of(2002, Month.AUGUST, 13, 1, 1),
                description = "Some description",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some descriptiosdfwfwefwefwefwefn",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some descriptiosdfwfwefwefwefwefn",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some descriptiosdfwfwefwefwefwefn",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            ),
            Transaction(
                id = 21,
                type = TransactionType(32, "Type1"),
                amount = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR")),
                date = LocalDateTime.now(),
                description = "Some descriptiosdfwfwefwefwefwefn",
                total = CurrencyAmount(amount.toBigDecimal(), Currency.getInstance("EUR"))
            )
        )
    )
}