package com.egitof.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.egitof.ui.theme.AppTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    elevation: Dp = 0.dp,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    onStartIconClick: () -> Unit = {},
    onEndIconClick: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    Surface(
        modifier = modifier,
        color = backgroundColor,
        shadowElevation = elevation
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (startIcon != null) {
                    StartIcon(
                        imageVector = startIcon,
                        onClick = onStartIconClick,
                    )
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = contentColor,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (endIcon != null) {
                    EndIcon(
                        imageVector = endIcon,
                        onClick = onEndIconClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun StartIcon(
    imageVector: ImageVector? = null,
    onClick: () -> Unit,
) {
    Icon(
        imageVector = imageVector,
        contentColor = Color.Black,
        onClick = onClick,
    )
}

@Composable
private fun EndIcon(
    imageVector: ImageVector? = null,
    onClick: () -> Unit,
) {
    Icon(
        imageVector = imageVector,
        contentColor = Color.Black,
        onClick = onClick,
    )
}

@Composable
private fun Icon(
    imageVector: ImageVector? = null,
    onClick: () -> Unit = {},
    contentColor: Color,
) {
    Surface(
        modifier = Modifier
            .defaultMinSize(minWidth = 72.dp),
        color = Color.Transparent
    ) {
        if (imageVector == null) return@Surface

        IconButton(
            modifier = Modifier.testTag("btn_start_icon_top_bar_test_id"),
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = imageVector,
                contentDescription = null,
                tint = contentColor,
            )
        }
    }
}

@Preview
@Composable
private fun TopBarPreview1() {
    AppTheme {
        TopBar(
            text = "Preview",
            contentColor = Color.Black,
            startIcon = Icons.Default.ArrowBack,
        )
    }
}

@Preview
@Composable
private fun TopBarPreview2() {
    AppTheme {
        TopBar(
            text = "Preview",
            contentColor = Color.Black,
            endIcon = Icons.Default.Close,
        )
    }
}

@Preview
@Composable
private fun TopBarPreview3() {
    AppTheme {
        TopBar(
            text = "Preview",
            contentColor = Color.Black,
        )
    }
}
