package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.chobitech.lib.android.FireSwitch

@Composable
fun WithTripleFireSwitch(
    fireSwitchAndEvent1: FireSwitchAndEvent,
    fireSwitchAndEvent2: FireSwitchAndEvent,
    fireSwitchAndEvent3: FireSwitchAndEvent,
    content: @Composable (fireSwitch1: FireSwitch, fireSwitch2: FireSwitch, fireSwitch3: FireSwitch) -> Unit
) {
    WithTripleFireSwitch(
        fireSwitch1 = fireSwitchAndEvent1.fireSwitch,
        onFired1 = fireSwitchAndEvent1.onFired,
        fireSwitch2 = fireSwitchAndEvent2.fireSwitch,
        onFired2 = fireSwitchAndEvent2.onFired,
        fireSwitch3 = fireSwitchAndEvent3.fireSwitch,
        onFired3 = fireSwitchAndEvent3.onFired,
        content = content
    )
}


@Composable
fun WithTripleFireSwitch(
    fireSwitch1: FireSwitch? = null,
    onFired1: () -> Unit,
    fireSwitch2: FireSwitch? = null,
    onFired2: () -> Unit,
    fireSwitch3: FireSwitch? = null,
    onFired3: () -> Unit,
    content: @Composable (fireSwitch1: FireSwitch, fireSwitch2: FireSwitch, fireSwitch3: FireSwitch) -> Unit
) {
    val swTriple = rememberTripleFireSwitch(
        fireSwitch1 = fireSwitch1,
        onFired1 = onFired1,
        fireSwitch2 = fireSwitch2,
        onFired2 = onFired2,
        fireSwitch3 = fireSwitch3,
        onFired3 = onFired3
    )

    content(swTriple.first, swTriple.second, swTriple.third)
}
