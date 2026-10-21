package com.chobitech.lib.android.fireswitch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun LaunchWithFireSwitch(
    fireSwitch: FireSwitch,
    enabled: Boolean = true,
    onFired: suspend () -> Unit
) {
    val innerEnabled by rememberUpdatedState(enabled)
    val innerOnFired by rememberUpdatedState(onFired)

    LaunchedEffect(fireSwitch) {
        fireSwitch.fired.collect {
            if (innerEnabled) {
                innerOnFired()
            }
        }
    }
}