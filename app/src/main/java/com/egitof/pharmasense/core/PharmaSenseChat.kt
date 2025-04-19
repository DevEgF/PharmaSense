package com.egitof.pharmasense.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.egitof.pharmasense.core.navigation.NavGraph
import com.egitof.ui.theme.AppTheme

@Composable
fun PharmaSenseApp(
    navController: NavHostController
) {
    AppTheme {
        NavGraph(navController)
    }
}