package com.egitof.access.recoverypassword.presentation

import androidx.compose.runtime.Composable

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
) {

}