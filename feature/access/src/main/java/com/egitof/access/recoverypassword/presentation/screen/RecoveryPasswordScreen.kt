package com.egitof.access.recoverypassword.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.egitof.access.R
import com.egitof.access.recoverypassword.presentation.viewmodel.RecoveryPasswordViewModel
import com.egitof.access.recoverypassword.presentation.viewmodel.event.RecoveryPasswordUiEvent
import com.egitof.access.recoverypassword.presentation.viewmodel.state.RecoveryPasswordUiState
import com.egitof.components.PrimaryButton
import com.egitof.components.PrimaryTextField
import com.egitof.components.TopBar
import com.egitof.templates.error.variants.InternetErrorScreen
import com.egitof.templates.error.variants.ServerErrorScreen
import com.egitof.ui.theme.AppTheme
import com.egitof.utils.domain.InputState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RecoveryPasswordRouter(
    onNavigateToLogin: () -> Unit,
) {
    RecoveryPasswordScreen(
        navigateBack = onNavigateToLogin
    )
}

@Composable
private fun RecoveryPasswordScreen(
    navigateBack: () -> Unit = {},
    viewModel: RecoveryPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val recoveryEmailMessage = stringResource(R.string.recovery_screen_send_email_to_recovery)

    LaunchedEffect(viewModel) {
        viewModel.event.collectLatest { event ->
            when(event) {
                RecoveryPasswordUiEvent.OnNavigateToLoginScreen -> navigateBack()
                RecoveryPasswordUiEvent.ShowSuccessMessage -> {
                    Toast.makeText(context, recoveryEmailMessage, Toast.LENGTH_SHORT).show()
                }
                RecoveryPasswordUiEvent.NavigateToLogin -> {
                    navigateBack()
                }
            }
        }
    }

    when(uiState.screenState) {
        RecoveryPasswordUiState.RecoveryPasswordScreenState.GenericError ->
            ServerErrorScreen(onTryAgainClick = viewModel::onSendRecoveryEmailClick)
        RecoveryPasswordUiState.RecoveryPasswordScreenState.NetworkError ->
            InternetErrorScreen(onTryAgainClick = viewModel::onSendRecoveryEmailClick)
        RecoveryPasswordUiState.RecoveryPasswordScreenState.Idle -> {
            Scaffold(
                modifier = Modifier
                    .systemBarsPadding()
                    .navigationBarsPadding()
                    .imePadding(),
                topBar = {
                    TopBar(
                        text = stringResource(R.string.recovery_screen_top_bar_title),
                        contentColor = Color.Black,
                        startIcon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        onStartIconClick = viewModel::onBackClick,
                    )
                },
                bottomBar = {
                    PrimaryButton(
                        modifier = Modifier.padding(16.dp),
                        text = stringResource(R.string.recovery_screen_btn),
                        onClick = viewModel::onSendRecoveryEmailClick
                    )
                }
            ) { paddings ->
                Column(
                    modifier = Modifier
                        .padding(paddings)
                        .systemBarsPadding()
                        .navigationBarsPadding()
                        .imePadding()
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(R.string.recovery_screen_top_bar_subtitle),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    EmailTextField(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        value = uiState.email,
                        onValueChange = viewModel::onEmailChanged,
                        onImeAction = ImeAction.Done,
                        isLoading = uiState.isLoading,
                        fieldsState = uiState.fieldState
                    )
                }
            }
        }
    }


}

@Composable
private fun EmailTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onImeAction: ImeAction,
    isLoading: Boolean,
    fieldsState: RecoveryPasswordUiState.RecoveryPasswordFieldsState
) {
    PrimaryTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.login_screen_textfield),
        leadingIcon = R.drawable.ic_envelope,
        keyboardType = KeyboardType.Email,
        imeAction = onImeAction,
        inputState = if(isLoading) InputState.Disabled else fieldsState.getRecoveryTextFieldState(),
        testTag = "text_field_login",
    )
}

@Composable
private fun RecoveryPasswordUiState.RecoveryPasswordFieldsState.getRecoveryTextFieldState(): InputState {
    return when (this) {
        RecoveryPasswordUiState.RecoveryPasswordFieldsState.InvalidEmailFormat -> InputState.Error(
            message = stringResource(R.string.recovery_screen_invalid_email_format)
        )
        RecoveryPasswordUiState.RecoveryPasswordFieldsState.EmptyFields -> InputState.Error(
            message = stringResource(R.string.recovery_screen_empty_fields)
        )
        else -> InputState.Default
    }
}

@Preview
@Composable
private fun RecoveryPasswordScreenPreview() {
    AppTheme {
        RecoveryPasswordScreen({})
    }
}
