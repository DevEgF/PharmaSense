package com.egitof.utils.domain

import androidx.compose.ui.Modifier

sealed class InputState {
    object Default : InputState()
    data class Error(val message: String?, val modifier: Modifier? = null) : InputState()
    object Success : InputState()
    object Disabled : InputState()
}