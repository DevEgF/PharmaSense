package com.egitof.pharmasense.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.egitof.navigation.PharmaSenseRoutes
import com.egitof.navigation.accessNavGraph
import com.egitof.pharmasense.core.navigation.extension.defaultTransitions

@Composable
fun NavGraph(
    navController: NavHostController
) {
    val initialRouter = PharmaSenseRoutes.Access.SplashScreen

    NavHost(
        navController = navController,
        startDestination = initialRouter,
        enterTransition = defaultTransitions().enter,
        exitTransition = defaultTransitions().exit,
        popEnterTransition = defaultTransitions().popEnter,
        popExitTransition = defaultTransitions().popExit,
    ) {
        accessNavGraph(navController)
    }
}