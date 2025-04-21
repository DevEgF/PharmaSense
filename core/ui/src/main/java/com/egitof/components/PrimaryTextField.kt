package com.egitof.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.egitof.ui.R
import com.egitof.ui.theme.AppTheme
import com.egitof.ui.theme.errorLight
import com.egitof.utils.domain.InputState
import com.egitof.utils.presentation.getVisualTransformationForPassword

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    @DrawableRes
    leadingIcon: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Email,
    placeholder: String = "",
    imeAction: ImeAction = ImeAction.Next,
    inputState: InputState = InputState.Default,
    testTag: String,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val isError = inputState is InputState.Error
    val errorMessage = (inputState as? InputState.Error)?.message

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .testTag(testTag),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = when (inputState) {
                        is InputState.Error -> errorLight
                        InputState.Disabled -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                        else -> MaterialTheme.colorScheme.primary
                    }
                )
            },
            leadingIcon = {
                leadingIcon?.let { icon ->
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = when (inputState) {
                            is InputState.Error -> errorLight
                            InputState.Disabled -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                            else -> MaterialTheme.colorScheme.primary
                        }
                    )
                }
            },
            trailingIcon = {
                if (keyboardType == KeyboardType.Password && value.isNotEmpty()) {
                    val visibilityIcon = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                    Icon(
                        painter = painterResource(id = visibilityIcon),
                        contentDescription = null,
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible },
                        tint = when (inputState) {
                            InputState.Disabled -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                            else -> MaterialTheme.colorScheme.primary
                        }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            singleLine = true,
            visualTransformation = keyboardType.getVisualTransformationForPassword(passwordVisible),
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = when (inputState) {
                    is InputState.Error -> errorLight
                    InputState.Disabled -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                    InputState.Success -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
                focusedBorderColor = when (inputState) {
                    InputState.Disabled -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                    else -> MaterialTheme.colorScheme.primary
                },
                disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f),
                disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
            ),
            enabled = inputState != InputState.Disabled
        )

        if (isError) {
            Text(
                text = errorMessage.orEmpty(),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                color = errorLight,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Composable
private fun PrimaryTextFieldPreview() {
    AppTheme {
        PrimaryTextField(
            value = "",
            onValueChange = {},
            keyboardType = KeyboardType.Password,
            inputState = InputState.Error("Invalid email format"),
            testTag = ""
        )
    }
}

@Preview
@Composable
private fun PrimaryTextFieldErrorPreview() {
    AppTheme {
        PrimaryTextField(
            value = "",
            onValueChange = {},
            keyboardType = KeyboardType.Email,
            testTag = ""
        )
    }
}
