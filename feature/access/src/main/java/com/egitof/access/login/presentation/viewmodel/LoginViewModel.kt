package com.egitof.access.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egitof.access.login.domain.usecase.LoginUseCase
import com.egitof.auth.domain.model.AuthError
import com.egitof.access.login.presentation.viewmodel.event.LoginEvent
import com.egitof.access.login.presentation.viewmodel.state.LoginUiState
import com.egitof.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    fun onEmailChanged(email: String) {
        _state.update {
            it.copy(
                email = email
            )
        }
        setFieldsStateToDefault()
    }

    fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(
                password = password
            )
        }
        setFieldsStateToDefault()
    }

    fun doLogin() {
        if(_state.value.isLoading) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    fieldsState = LoginUiState.LoginFieldsState.Default,
                )
            }

            when (val result = loginUseCase(
                email = _state.value.email,
                password = _state.value.password
            )) {
                is Resource.Error -> handleFailure(result.error)
                is Resource.Success -> handleSuccess()
            }
        }
    }

    private fun handleSuccess() {
        _state.update {
            it.copy(
                isLoading = false,
            )
        }
        emitEvent(LoginEvent.Success)
    }

    private fun handleFailure(error: AuthError) {
        _state.update {
            it.copy(
                isLoading = false,
            )
        }
        when (error) {
            AuthError.EmptyFields -> setUiStateErrorForEmptyField()
            AuthError.InvalidEmailFormat -> setUiStateErrorForInvalidEmailFormat()
            is AuthError.NetworkError -> setUiStateNetworkError()
            is AuthError.InvalidCredentials -> setUiStateErrorInvalidCredentials()
            else -> setUIStateGenericError()
        }
    }

    private fun setUiStateErrorForInvalidEmailFormat() {
        _state.update {
            it.copy(
                fieldsState = LoginUiState.LoginFieldsState.InvalidEmailFormat
            )
        }
    }

    private fun setUiStateErrorForEmptyField() {
        _state.update {
            it.copy(
                fieldsState = LoginUiState.LoginFieldsState.EmptyFields
            )
        }
    }

    private fun setUiStateErrorInvalidCredentials() {
        _state.update {
            it.copy(
                fieldsState = LoginUiState.LoginFieldsState.InvalidCredentials
            )
        }
    }

    private fun setUiStateNetworkError() {
        _state.update {
            it.copy(
                screenState = LoginUiState.AuthScreenState.NetworkError
            )
        }
    }

    private fun setUIStateGenericError() {
        _state.update {
            it.copy(
                screenState = LoginUiState.AuthScreenState.GenericError
            )
        }
    }

    private fun setFieldsStateToDefault() {
        _state.update {
            it.copy(
                fieldsState = LoginUiState.LoginFieldsState.Default
            )
        }
    }

    private fun emitEvent(event: LoginEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}