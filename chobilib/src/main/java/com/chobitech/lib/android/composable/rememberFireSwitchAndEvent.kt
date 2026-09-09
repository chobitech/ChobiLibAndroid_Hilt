package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.chobitech.lib.android.FireSwitch

@Composable
fun rememberFireSwitchAndEvent(
    outerFierSwitch: FireSwitch? = null,
    onFired: () -> Unit
): FireSwitchAndEvent {
    val switch = rememberFireSwitch(
        outerFierSwitch = outerFierSwitch,
        onFired = onFired
    )

    return remember(switch, onFired) {
        FireSwitchAndEvent(switch, onFired)
    }
}