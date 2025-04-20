package com.egitof.access.login.presentation.viewmodel.event

sealed class LoginEvent {
    object Success : LoginEvent()
}