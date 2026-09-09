package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.chobitech.lib.android.FireSwitch

@Composable
fun rememberDoubleFireSwitch(
    fireSwitch1: FireSwitch? = null,
    onFired1: () -> Unit,
    fireSwitch2: FireSwitch? = null,
    onFired2: () -> Unit
): Pair<FireSwitch, FireSwitch> {

    val sw1 = rememberFireSwitch(
        outerFierSwitch = fireSwitch1,
        onFired = onFired1
    )

    val sw2 = rememberFireSwitch(
        outerFierSwitch = fireSwitch2,
        onFired = onFired2
    )

    return remember(sw1, sw2) {
        Pair(sw1, sw2)
    }
}