package com.egitof.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egitof.auth.domain.model.AuthError
import com.egitof.auth.domain.usecase.CurrentUserUseCase
import com.egitof.auth.domain.usecase.LoginUseCase
import com.egitof.auth.domain.usecase.LogoutUseCase
import com.egitof.auth.presentation.event.AuthEvent
import com.egitof.auth.presentation.state.AuthState
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
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<AuthEvent>()
    val event = _event.asSharedFlow()

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = loginUseCase(email, password)) {
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
        emitEvent(AuthEvent.Success)
    }

    //TODO handle failure create
    private fun handleFailure(error: AuthError) {

        _state.update {
            it.copy(
                isLoading = false,
                screenState = AuthState.AuthScreenState.Error
            )
        }
        emitEvent(AuthEvent.Failure)
    }

    private fun emitEvent(event: AuthEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}