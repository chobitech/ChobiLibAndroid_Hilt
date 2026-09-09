package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.chobitech.lib.android.FireSwitch

@Composable
fun rememberTripleFireSwitch(
    fireSwitch1: FireSwitch? = null,
    onFired1: () -> Unit,
    fireSwitch2: FireSwitch? = null,
    onFired2: () -> Unit,
    fireSwitch3: FireSwitch? = null,
    onFired3: () -> Unit
): Triple<FireSwitch, FireSwitch, FireSwitch> {

    val sw1 = rememberFireSwitch(
        outerFierSwitch = fireSwitch1,
        onFired = onFired1
    )

    val sw2 = rememberFireSwitch(
        outerFierSwitch = fireSwitch2,
        onFired = onFired2
    )

    val sw3 = rememberFireSwitch(
        outerFierSwitch = fireSwitch3,
        onFired = onFired3
    )

    return remember(sw1, sw2, sw3) {
        Triple(sw1, sw2, sw3)
    }
}