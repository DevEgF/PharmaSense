package com.egitof.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.egitof.access.login.presentation.screen.LoginScreenRouter
import com.egitof.access.recoverypassword.presentation.screen.RecoveryPasswordRouter
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
            navigateBack = {
                navController.popBackStack()
            },
            navigateToLoginScreen = {
                navController.navigate(AccessRoutes.Access.LoginScreen) {
                    popUpTo(AccessRoutes.Access.RecoveryPasswordScreen) { inclusive = true }
                }
            }
        )
    }
}
