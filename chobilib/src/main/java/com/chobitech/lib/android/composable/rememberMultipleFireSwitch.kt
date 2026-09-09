package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import com.chobitech.lib.android.FireSwitch

@Composable
@JvmName("rememberMultipleFireSwitchFromEvents")
fun rememberMultipleFireSwitch(
    fireSwitchAndEvents: List<FireSwitchAndEvent>
): List<FireSwitch> {
    val swList = remember(fireSwitchAndEvents) {
        fireSwitchAndEvents.map { it.fireSwitch }
    }

    for (fse in fireSwitchAndEvents) {
        key(fse.fireSwitch.uuid) {
            FireSwitchAndEventObserver(fse)
        }
    }

    return swList
}

@Composable
@JvmName("rememberMultipleFireSwitchFromCallbacks")
fun rememberMultipleFireSwitch(
    onFiredList: List<() -> Unit>
): List<FireSwitch> {
    val fseList = remember(onFiredList) {
        onFiredList.map {
            FireSwitchAndEvent(FireSwitch(), it)
        }
    }

    return rememberMultipleFireSwitch(
        fireSwitchAndEvents = fseList
    )
}
