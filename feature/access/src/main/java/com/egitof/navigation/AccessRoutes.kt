package com.egitof.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AccessRoutes {

    class Access {
        @Serializable
        data object SplashScreen : AccessRoutes()
        @Serializable
        data object LoginScreen : AccessRoutes()
    }
}
