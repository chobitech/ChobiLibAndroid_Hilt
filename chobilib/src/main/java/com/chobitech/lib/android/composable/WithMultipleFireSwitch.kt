package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import com.chobitech.lib.android.FireSwitch


@Composable
@JvmName("WithMultipleFireSwitchWithEvents")
fun WithMultipleFireSwitch(
    fireSwitchEventList: List<FireSwitchAndEvent>,
    content: @Composable (fireSwitchList: List<FireSwitch>) -> Unit
) {
    val swList = rememberMultipleFireSwitch(
        fireSwitchAndEvents = fireSwitchEventList
    )

    content(swList)
}

@Composable
@JvmName("WithMultipleFireSwitchWithCallbacks")
fun WithMultipleFireSwitch(
    onFiredList: List<() -> Unit>,
    content: @Composable (fireSwitchList: List<FireSwitch>) -> Unit
) {
    val swList = rememberMultipleFireSwitch(
        onFiredList = onFiredList
    )

    content(swList)
}

