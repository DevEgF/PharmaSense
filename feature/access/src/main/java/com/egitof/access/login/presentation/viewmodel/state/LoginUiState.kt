package com.egitof.access.login.presentation.viewmodel.state

data class LoginUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isEnableConfirmButton: Boolean = false,
    val fieldsState: LoginFieldsState = LoginFieldsState.Default,
    val screenState: AuthScreenState = AuthScreenState.Idle,
) {
    sealed interface AuthScreenState {
        object Idle : AuthScreenState
        object NetworkError : AuthScreenState
        object GenericError : AuthScreenState
    }
    sealed interface LoginFieldsState {
        object Default : LoginFieldsState
        object InvalidEmailFormat : LoginFieldsState
        object EmptyFields : LoginFieldsState
        object InvalidCredentials : LoginFieldsState
    }
}