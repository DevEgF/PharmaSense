package com.egitof.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerVertical(dp: Dp) {
    Spacer(modifier = Modifier.height(dp))
}

@Composable
fun SpacerHorizontal(dp: Dp) {
    Spacer(modifier = Modifier.width(dp))
}
