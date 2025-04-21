package com.egitof.templates.success

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.egitof.ui.R
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(
    onFinish: () -> Unit = {},
    title: String,
    animationRes: Int = R.raw.check,
    animationDuration: Int = 800,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )

    var showContent by remember { mutableStateOf(false) }
    var shouldNavigate by remember { mutableStateOf(false) }

    LaunchedEffect(progress) {
        if (progress >= 0.99f && !showContent) {
            showContent = true
            shouldNavigate = true
        }
    }

    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            delay(1_000)
            onFinish()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier.size(280.dp),
                composition = composition,
                progress = { progress }
            )

            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn(tween(animationDuration))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Title(text = title)
                }
            }
        }
    }
}

@Composable
private fun Title(text: String) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
    )
}
