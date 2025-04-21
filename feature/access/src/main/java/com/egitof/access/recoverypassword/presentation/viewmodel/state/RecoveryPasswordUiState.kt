package com.egitof.access.recoverypassword.presentation.viewmodel.state

data class RecoveryPasswordUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val fieldState : RecoveryPasswordFieldsState = RecoveryPasswordFieldsState.Default,
    val screenState: RecoveryPasswordScreenState = RecoveryPasswordScreenState.Idle,
) {
    sealed interface RecoveryPasswordFieldsState {
        object Default : RecoveryPasswordFieldsState
        object InvalidEmailFormat : RecoveryPasswordFieldsState
        object EmptyFields : RecoveryPasswordFieldsState
        object InvalidCredentials : RecoveryPasswordFieldsState
    }

    sealed interface RecoveryPasswordScreenState {
        object Idle : RecoveryPasswordScreenState
        object NetworkError : RecoveryPasswordScreenState
        object GenericError : RecoveryPasswordScreenState
    }
}
