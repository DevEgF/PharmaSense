package com.egitof.access.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.egitof.access.R
import com.egitof.animation.breathingScale
import com.egitof.components.SpacerHorizontal
import kotlinx.coroutines.delay

@Composable
fun SplashScreenRouter(
    onNavigateToLogin: () -> Unit = {}
) {
    SplashScreen()

    LaunchedEffect(Unit) {
        delay(1500)
        onNavigateToLogin()
    }
}

@Composable
private fun SplashScreen() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceBright)
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            val scale = breathingScale()

            Image(
                modifier = Modifier
                    .size(300.dp)
                    .scale(scale),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_safety),
                contentDescription = null
            )

            SpacerHorizontal(8.dp)

            Text(
                text = stringResource(R.string.splash_safety_info),
                textAlign = TextAlign.Center,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

