package com.egitof.access.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.egitof.auth.presentation.viewmodel.AuthViewModel
import androidx.compose.runtime.getValue
import com.egitof.auth.presentation.event.AuthEvent
import com.egitof.ui.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreenRouter(
    navigateToChat: () -> Unit = {},
    navigateToFailureScreen: () -> Unit = {},
) {
    LoginScreen(
        navigateToChat = navigateToChat,
        navigateToFailureScreen = navigateToFailureScreen
    )
}


@Composable
private fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToChat: () -> Unit = {},
    navigateToFailureScreen: () -> Unit = {},
) {
    val authState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.event.collectLatest { event ->
            when (event) {
                AuthEvent.Success -> navigateToChat()
                AuthEvent.Failure -> navigateToFailureScreen()
            }
        }
    }

    Scaffold(
        bottomBar = {
            Button(
                onClick = { viewModel.doLogin("test@test.com.br", "12345") },
            ) {
                Text("Login")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.surfaceBright)
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen()
    }
}