package com.egitof.access.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.egitof.access.login.presentation.viewmodel.LoginViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.egitof.access.R
import com.egitof.access.login.presentation.viewmodel.event.LoginEvent
import com.egitof.access.login.presentation.viewmodel.state.LoginUiState
import com.egitof.components.PrimaryButton
import com.egitof.components.PrimaryTextField
import com.egitof.templates.error.variants.InternetErrorScreen
import com.egitof.templates.error.variants.ServerErrorScreen
import com.egitof.ui.theme.AppTheme
import com.egitof.utils.domain.InputState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreenRouter(
    navigateToChat: () -> Unit = {},
    navigateToForgotPassword: () -> Unit = {}
) {
    LoginScreen(
        navigateToChat = navigateToChat,
        navigateToForgotPassword = navigateToForgotPassword
    )
}

@Composable
private fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToChat: () -> Unit = {},
    navigateToForgotPassword: () -> Unit = {}
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.event.collectLatest { event ->
            when (event) {
                LoginEvent.Success -> navigateToChat()
            }
        }
    }

    when(uiState.screenState) {
        LoginUiState.AuthScreenState.GenericError ->
            ServerErrorScreen(onTryAgainClick = viewModel::doLogin)
        LoginUiState.AuthScreenState.NetworkError ->
            InternetErrorScreen(onTryAgainClick = viewModel::doLogin)
        LoginUiState.AuthScreenState.Idle -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .navigationBarsPadding()
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null
                )

                LoginEmailTextField(
                    value = uiState.email,
                    onValueChange = viewModel::onEmailChanged,
                    onImeAction = ImeAction.Next,
                    isLoading = uiState.isLoading,
                    fieldsState = uiState.fieldsState
                )

                Spacer(modifier = Modifier.height(18.dp))

                LoginPasswordTextField(
                    value = uiState.password,
                    onValueChange = viewModel::onPasswordChanged,
                    onImeAction = ImeAction.Done,
                    isLoading = uiState.isLoading,
                    fieldsState = uiState.fieldsState
                )

                Spacer(modifier = Modifier.height(48.dp))

                PrimaryButton(
                    text = stringResource(R.string.login_screen_btn),
                    modifier = Modifier.padding(16.dp),
                    onClick = viewModel::doLogin,
                    isLoading = uiState.isLoading
                )

                val forgotAccountPassword = stringResource(R.string.feature_forgot_password_account)
                val actionText = stringResource(R.string.feature_forgot_password_account_action)

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = buildAnnotatedString {
                        append("$forgotAccountPassword ")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(actionText)
                        }
                    },
                    modifier = Modifier.clickable { navigateToForgotPassword() }
                )
            }
        }
    }
}

@Composable
private fun LoginEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onImeAction: ImeAction,
    isLoading: Boolean,
    fieldsState: LoginUiState.LoginFieldsState,
) {
    PrimaryTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.login_screen_textfield),
        leadingIcon = R.drawable.ic_envelope,
        keyboardType = KeyboardType.Email,
        imeAction = onImeAction,
        inputState = if(isLoading) InputState.Disabled else fieldsState.getLoginTextFieldState(),
        testTag = "text_field_login",
    )
}

@Composable
private fun LoginPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onImeAction: ImeAction,
    isLoading: Boolean,
    fieldsState: LoginUiState.LoginFieldsState,
) {
    PrimaryTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.login_screen_password_placeholder),
        leadingIcon = R.drawable.ic_lock,
        keyboardType = KeyboardType.Password,
        imeAction = onImeAction,
        inputState = if(isLoading) InputState.Disabled else fieldsState.getPasswordTextFieldState(),
        testTag = "text_field_password",
    )
}

@Composable
private fun LoginUiState.LoginFieldsState.getLoginTextFieldState(): InputState {
    return when (this) {
        is LoginUiState.LoginFieldsState.Default -> InputState.Default
        else -> InputState.Error(null)
    }
}

@Composable
private fun LoginUiState.LoginFieldsState.getPasswordTextFieldState(): InputState {
    return when (this) {
        LoginUiState.LoginFieldsState.Default -> InputState.Default
        LoginUiState.LoginFieldsState.EmptyFields -> InputState.Error(
            message = stringResource(R.string.login_screen_empty_fields)
        )
        LoginUiState.LoginFieldsState.InvalidCredentials -> InputState.Error(
            message = stringResource(R.string.login_screen_invalid_credentials)
        )
        LoginUiState.LoginFieldsState.InvalidEmailFormat -> InputState.Error(
            message = stringResource(R.string.login_screen_invalid_email_format)
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen()
    }
}