package com.egitof.access.recoverypassword.presentation.viewmodel.event

sealed class RecoveryPasswordUiEvent {
    object OnNavigateToLoginScreen : RecoveryPasswordUiEvent()
    object ShowSuccessMessage : RecoveryPasswordUiEvent()
    object NavigateToLogin : RecoveryPasswordUiEvent()
}