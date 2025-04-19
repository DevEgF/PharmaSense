package com.egitof.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.egitof.access.login.presentation.LoginScreenRouter
import com.egitof.access.splash.presentation.SplashScreenRouter

fun NavGraphBuilder.accessNavGraph(
    navController: NavController
) {
    this.composable<PharmaSenseRoutes.Access.SplashScreen> {
        SplashScreenRouter(
            onNavigateToLogin = {
                navController.navigate(PharmaSenseRoutes.Access.LoginScreen) {
                    popUpTo(PharmaSenseRoutes.Access.SplashScreen) { inclusive = true }
                }
            }
        )
    }

    this.composable<PharmaSenseRoutes.Access.LoginScreen> {
        LoginScreenRouter()
    }
}
