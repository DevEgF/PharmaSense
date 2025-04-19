package com.egitof.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class PharmaSenseRoutes {

    class Access {
        @Serializable
        data object SplashScreen : PharmaSenseRoutes()
        @Serializable
        data object LoginScreen : PharmaSenseRoutes()
    }
}
