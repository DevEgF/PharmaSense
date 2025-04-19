package com.egitof.auth.presentation.state

data class AuthState(
    val isLoading: Boolean = false,
    val isEnableConfirmButton: Boolean = false,
    val isInvalidInput: Boolean = false,
    val screenState: AuthScreenState = AuthScreenState.Idle,
) {
    sealed interface AuthScreenState {
        object Idle : AuthScreenState
        object Error : AuthScreenState
    }
}