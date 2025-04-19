package com.egitof.pharmasense.core.navigation.extension

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry

data class NavTransitions(
    val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
    val popEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    val popExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition,
)

fun defaultTransitions() = NavTransitions(
    enter = { slideInTo(AnimatedContentTransitionScope.SlideDirection.Start) },
    exit = { slideOutTo(AnimatedContentTransitionScope.SlideDirection.Start) },
    popEnter = { slideInTo(AnimatedContentTransitionScope.SlideDirection.End) },
    popExit = { slideOutTo(AnimatedContentTransitionScope.SlideDirection.End) },
)
