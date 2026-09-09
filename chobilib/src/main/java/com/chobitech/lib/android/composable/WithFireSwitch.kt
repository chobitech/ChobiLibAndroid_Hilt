package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import com.chobitech.lib.android.FireSwitch

@Composable
fun WithFireSwitch(
    fireSwitch: FireSwitch? = null,
    onFired: () -> Unit,
    content: @Composable (fireSwitch: FireSwitch) -> Unit
) {
    val currentFireSwitch = rememberFireSwitch(
        outerFierSwitch = fireSwitch,
        onFired = onFired
    )

    content(currentFireSwitch)
}
