package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.chobitech.lib.android.FireSwitch

@Immutable
data class FireSwitchAndEvent(
    val fireSwitch: FireSwitch,
    val onFired: () -> Unit
)


@Composable
internal fun FireSwitchAndEventObserver(
    fse: FireSwitchAndEvent
) {
    rememberFireSwitch(fse.fireSwitch, fse.onFired)
}
