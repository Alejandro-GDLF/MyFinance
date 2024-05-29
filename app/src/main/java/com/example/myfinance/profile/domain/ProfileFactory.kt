package com.example.myfinance.profile.domain

import javax.inject.Inject

class ProfileFactory @Inject constructor() {
    fun create(name: String, email: String): Profile {
        return Profile(
            id = null,
            name = name,
            email = email
        )
    }
}