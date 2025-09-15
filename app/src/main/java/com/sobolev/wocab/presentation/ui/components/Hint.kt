package com.sobolev.wocab.presentation.ui.components

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

@Composable
fun BottomHint(modifier: Modifier = Modifier) {
    Text(
        text = "Swipe to see more",
        color = LocalContentColor.current.copy(alpha = 0.45f),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(bottom = 36.dp)
    )
}


