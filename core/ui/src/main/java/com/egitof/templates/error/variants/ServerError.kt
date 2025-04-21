package com.egitof.templates.error.variants

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.egitof.templates.error.ErrorScreen
import com.egitof.ui.R
import com.egitof.ui.theme.AppTheme

@Composable
fun ServerErrorScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onTryAgainClick: () -> Unit,
    shouldBlockBackOnLoading: Boolean = true,
) {
    ErrorScreen(
        modifier = modifier,
        title = stringResource(R.string.text_template_error_server),
        description = stringResource(R.string.text_template_error_server_desc),
        buttonText = stringResource(R.string.btn_template_error_try_again),
        isLoading = isLoading,
        blockedBackOnLoading = shouldBlockBackOnLoading,
        onButtonClick = onTryAgainClick
    )
}

@Preview
@Composable
private fun ServerErrorScreenPreview() {
    AppTheme {
        ServerErrorScreen(
            onTryAgainClick = { },
        )
    }
}