package com.egitof.pharmasense.core.entrypoint

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.egitof.access.user.data.CurrentUserStateManager
import com.egitof.pharmasense.core.navigation.NavGraph
import com.egitof.ui.theme.AppTheme

@Composable
fun PharmaSenseApp(
    navController: NavHostController,
    currentUserStateManager: CurrentUserStateManager
) {
    AppTheme {
        NavGraph(
            navController,
            currentUserStateManager
        )
    }
}