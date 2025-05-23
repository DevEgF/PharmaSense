package com.egitof.pharmasense.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.egitof.access.user.data.CurrentUserStateManager
import com.egitof.navigation.AccessRoutes
import com.egitof.navigation.accessNavGraph
import com.egitof.pharmasense.core.navigation.extension.defaultTransitions

@Composable
fun NavGraph(
    navController: NavHostController,
    currentUserStateManager: CurrentUserStateManager
) {
    val isLoggedIn = remember { currentUserStateManager.isLoggedIn }

    val initialRouter = if (isLoggedIn) {
        // TODO IMPLEMENT NAVIGATION TO CHAT HOME
    } else {
        AccessRoutes.Access.SplashScreen
    }

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