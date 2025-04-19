package com.egitof.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.egitof.ui.theme.AppTheme

@Composable
fun BotMessage(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.onSecondaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    12.dp
                )
        )
    }
}

@Preview
@Composable
private fun BotMessagePreview() {
    AppTheme {
        BotMessage("Estou bem, obrigado por perguntar")
    }
}