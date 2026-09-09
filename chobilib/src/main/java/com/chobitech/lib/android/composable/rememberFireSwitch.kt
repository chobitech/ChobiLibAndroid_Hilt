package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.chobitech.lib.android.FireSwitch

@Composable
fun rememberFireSwitch(
    outerFierSwitch: FireSwitch? = null,
    onFired: () -> Unit
): FireSwitch {
    val innerSwitch = remember {
        FireSwitch()
    }

    val switch = outerFierSwitch ?: innerSwitch

    val currentOnFired by rememberUpdatedState(onFired)

    LaunchedEffect(switch) {
        switch.fired.collect {
            currentOnFired()
        }
    }

    return switch
}