package com.egitof.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.egitof.ui.theme.AppTheme

@Composable
fun CustomSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { data ->
        CustomSnackbar {
            Text(
                text = data.visuals.message,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
        snackbar = snackbar
    )
}

@Composable
fun CustomSnackbar(
    modifier: Modifier = Modifier,
    action: @Composable (() -> Unit)? = null,
    actionOnNewLine: Boolean = false,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = if (actionOnNewLine) Arrangement.spacedBy(8.dp) else Arrangement.Center
        ) {
            content()
            if (action != null) {
                if (actionOnNewLine) {
                    Spacer(modifier = Modifier.height(4.dp))
                }
                action()
            }
        }
    }
}

@Preview
@Composable
fun CustomSnackbarPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            CustomSnackbar {
                Text("Esta é uma mensagem de snackbar customizada", color = Color.White)
            }
        }
    }
}

suspend fun SnackbarHostState.showCustomSnackbar(
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    this.showSnackbar(
        message = message,
        actionLabel = actionLabel,
        duration = duration
    )
}
