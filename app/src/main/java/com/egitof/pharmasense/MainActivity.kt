package com.egitof.pharmasense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.egitof.access.user.data.CurrentUserStateManager
import com.egitof.pharmasense.core.entrypoint.PharmaSenseApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var currentUserStateManager: CurrentUserStateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PharmaSenseApp(
                navController = rememberNavController(),
                currentUserStateManager = currentUserStateManager
            )
        }
    }
}
