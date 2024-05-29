package com.example.myfinance.core

interface Formatter<T> {
    fun format(obj: T): String
}