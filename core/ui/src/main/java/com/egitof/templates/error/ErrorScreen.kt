package com.egitof.templates.error

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.egitof.components.PrimaryButton

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    buttonText: String,
    isLoading: Boolean = false,
    blockedBackOnLoading: Boolean = true,
    onButtonClick: () -> Unit,
) {
    if (isLoading && blockedBackOnLoading) {
        BackHandler(enabled = true, onBack = {})
    }

    Scaffold(
        modifier = modifier
            .systemBarsPadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(text = title)
            Description(text = description)
            PrimaryButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = buttonText,
                isLoading = isLoading,
                onClick = onButtonClick,
            )
        }
    }
}

@Composable
private fun Title(text: String) {
    Text(
        modifier = Modifier.padding(horizontal = 24.dp),
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
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
