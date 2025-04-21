package com.egitof.access.recoverypassword.presentation.viewmodel.event

sealed class RecoveryPasswordUiEvent {
    object NavigateBack : RecoveryPasswordUiEvent()
    object NavigateToLogin : RecoveryPasswordUiEvent()
}