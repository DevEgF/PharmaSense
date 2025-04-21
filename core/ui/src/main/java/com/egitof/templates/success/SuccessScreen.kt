package com.egitof.templates.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.egitof.ui.R

@Composable
fun SuccessScreen(
    onFinish: () -> Unit = {},
    title: String,
    description: String
) {
    val isPlaying by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.check))

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        isPlaying = isPlaying
    )

    LaunchedEffect(progress) {
        if (progress >= 0.99f) {
            onFinish()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress }
            )

            Title(text = title)
            Description(text = description)
        }
    }
}

@Composable
private fun Title(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun Description(text: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 8.dp, bottom = 32.dp),
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
    )
}