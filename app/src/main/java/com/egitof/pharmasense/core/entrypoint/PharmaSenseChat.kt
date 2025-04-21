package com.egitof.pharmasense.core.entrypoint

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.egitof.access.login.data.AuthStateManager
import com.egitof.pharmasense.core.navigation.NavGraph
import com.egitof.ui.theme.AppTheme

@Composable
fun PharmaSenseApp(
    navController: NavHostController,
    authStateManager: AuthStateManager
) {
    AppTheme {
        NavGraph(
            navController,
            authStateManager
        )
    }
}