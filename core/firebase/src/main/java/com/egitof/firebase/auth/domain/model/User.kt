package com.egitof.firebase.auth.domain.model

data class User(
    val id: String,
    val email: String,
    val isEmailVerified: Boolean
)