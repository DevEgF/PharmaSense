package com.egitof.auth.presentation.event

sealed class AuthEvent {
    object Success : AuthEvent()
    object Failure : AuthEvent()
}