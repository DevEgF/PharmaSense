package com.egitof.access.recoverypassword.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egitof.access.login.presentation.viewmodel.state.LoginUiState
import com.egitof.access.recoverypassword.domain.usecase.RecoveryPasswordUseCase
import com.egitof.access.recoverypassword.presentation.viewmodel.event.RecoveryPasswordUiEvent
import com.egitof.access.recoverypassword.presentation.viewmodel.event.RecoveryPasswordUiEvent.OnNavigateToLoginScreen
import com.egitof.access.recoverypassword.presentation.viewmodel.state.RecoveryPasswordUiState
import com.egitof.access.recoverypassword.presentation.viewmodel.state.RecoveryPasswordUiState.RecoveryPasswordFieldsState.EmptyFields
import com.egitof.access.recoverypassword.presentation.viewmodel.state.RecoveryPasswordUiState.RecoveryPasswordFieldsState.InvalidEmailFormat
import com.egitof.auth.domain.model.AuthError
import com.egitof.utils.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor(
    private val useCase: RecoveryPasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow(RecoveryPasswordUiState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<RecoveryPasswordUiEvent>()
    val event = _event.asSharedFlow()

    fun onBackClick() {
        emitEvent(OnNavigateToLoginScreen)
    }

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
        setFieldsStateToDefault()
    }

    fun onSendRecoveryEmailClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    fieldState = RecoveryPasswordUiState.RecoveryPasswordFieldsState.Default,
                )
            }

            when (val result = useCase.invoke(state.value.email)) {
                is Resource.Error -> handleFailure(result.error)
                is Resource.Success -> handleSuccess()
            }
        }
    }

    private fun handleSuccess() {
        _state.update {
            it.copy(
                isLoading = false,
                screenState = RecoveryPasswordUiState.RecoveryPasswordScreenState.Idle
            )
        }
        emitEvent(RecoveryPasswordUiEvent.ShowSuccessMessage)
        emitEvent(RecoveryPasswordUiEvent.NavigateToLogin)
    }

    private fun handleFailure(error: AuthError) {
        _state.update {
            it.copy(
                isLoading = false
            )
        }
        when(error) {
            AuthError.EmptyFields -> setUiStateErrorForEmptyField()
            AuthError.InvalidEmailFormat -> setUiStateErrorForInvalidEmailFormat()
            is AuthError.GenericError -> setUiStateGenericError()
            is AuthError.NetworkError -> setUiStateNetworkError()
            else -> Unit
        }
    }

    private fun setUiStateErrorForInvalidEmailFormat() {
        _state.update {
            it.copy(
                fieldState = InvalidEmailFormat
            )
        }
    }

    private fun setUiStateErrorForEmptyField() {
        _state.update {
            it.copy(
                fieldState = EmptyFields
            )
        }
    }

    private fun setUiStateNetworkError() {
        _state.update {
            it.copy(
                screenState = RecoveryPasswordUiState.RecoveryPasswordScreenState.NetworkError
            )
        }
    }

    private fun setUiStateGenericError() {
        _state.update {
            it.copy(
                screenState = RecoveryPasswordUiState.RecoveryPasswordScreenState.GenericError
            )
        }
    }

    private fun setFieldsStateToDefault() {
        _state.update { it.copy(fieldState = RecoveryPasswordUiState.RecoveryPasswordFieldsState.Default) }
    }

    private fun emitEvent(event: RecoveryPasswordUiEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}