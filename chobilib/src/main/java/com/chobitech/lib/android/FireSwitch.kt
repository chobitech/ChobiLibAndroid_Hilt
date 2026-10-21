package com.chobitech.lib.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import java.util.UUID

class FireSwitch {

    internal val uuid = UUID.randomUUID()

    private val _switch = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val fired = _switch.asSharedFlow()

    fun fire() {
        _switch.tryEmit(Unit)
    }

    @Composable
    fun LaunchOnFired(
        onFired: suspend () -> Unit
    ) {
        val innerOnFired by rememberUpdatedState(onFired)

        LaunchedEffect(Unit) {
            fired.collect {
                innerOnFired()
            }
        }
    }
}
