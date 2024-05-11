package com.example.myfinance.core

interface Mapper<FROM, TO> {
    fun from(item: TO): FROM
    fun to(item: FROM): TO
}