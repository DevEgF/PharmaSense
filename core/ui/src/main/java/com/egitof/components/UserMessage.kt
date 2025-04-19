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
import androidx.compose.ui.unit.dp
import com.egitof.ui.theme.AppTheme

@Composable
fun UserMessage(message: String) {
    Row(
       modifier = Modifier
           .fillMaxWidth()
           .padding(8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    12.dp
                )
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun UserMessagePreview() {
    AppTheme {
        UserMessage("Olá")
    }
}