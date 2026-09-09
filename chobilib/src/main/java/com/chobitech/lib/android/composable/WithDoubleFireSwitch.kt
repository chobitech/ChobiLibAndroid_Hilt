package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.chobitech.lib.android.FireSwitch

@Composable
fun WithDoubleFireSwitch(
    fireSwitchAndEvent1: FireSwitchAndEvent,
    fireSwitchAndEvent2: FireSwitchAndEvent,
    content: @Composable (fireSwitch1: FireSwitch, fireSwitch2: FireSwitch) -> Unit
) {
    WithDoubleFireSwitch(
        fireSwitch1 = fireSwitchAndEvent1.fireSwitch,
        onFired1 = fireSwitchAndEvent1.onFired,
        fireSwitch2 = fireSwitchAndEvent2.fireSwitch,
        onFired2 = fireSwitchAndEvent2.onFired,
        content = content
    )
}


@Composable
fun WithDoubleFireSwitch(
    fireSwitch1: FireSwitch? = null,
    onFired1: () -> Unit,
    fireSwitch2: FireSwitch? = null,
    onFired2: () -> Unit,
    content: @Composable (fireSwitch1: FireSwitch, fireSwitch2: FireSwitch) -> Unit
) {
    val swPair = rememberDoubleFireSwitch(
        fireSwitch1 = fireSwitch1,
        onFired1 = onFired1,
        fireSwitch2 = fireSwitch2,
        onFired2 = onFired2
    )

    content(swPair.first, swPair.second)
}
