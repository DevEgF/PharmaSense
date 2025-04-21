package com.egitof.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.egitof.access.login.presentation.screen.LoginScreenRouter
import com.egitof.access.recoverypassword.presentation.RecoveryPasswordRouter
import com.egitof.access.splash.presentation.SplashScreenRouter

fun NavGraphBuilder.accessNavGraph(
    navController: NavController
) {
    this.composable<AccessRoutes.Access.SplashScreen> {
        SplashScreenRouter(
            onNavigateToLogin = {
                navController.navigate(AccessRoutes.Access.LoginScreen) {
                    popUpTo(AccessRoutes.Access.SplashScreen) { inclusive = true }
                }
            }
        )
    }

    this.composable<AccessRoutes.Access.LoginScreen> {
        LoginScreenRouter(
            navigateToForgotPassword = {
                navController.navigate(AccessRoutes.Access.RecoveryPasswordScreen)
            }
        )
    }

    this.composable<AccessRoutes.Access.RecoveryPasswordScreen> {
        RecoveryPasswordRouter(
            onNavigateToLogin = {
                navController.popBackStack()
            }
        )
    }
}
