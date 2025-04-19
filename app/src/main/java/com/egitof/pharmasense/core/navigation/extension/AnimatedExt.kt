package com.egitof.pharmasense.core.navigation.extension

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

private const val ANIMATION_DURATION = 450

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInTo(
    direction: AnimatedContentTransitionScope.SlideDirection
): EnterTransition {
    return this.slideIntoContainer(
        towards = direction,
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutTo(
    direction: AnimatedContentTransitionScope.SlideDirection
): ExitTransition {
    return this.slideOutOfContainer(
        towards = direction,
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    )
}