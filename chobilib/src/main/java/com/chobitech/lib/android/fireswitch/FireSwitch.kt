package com.chobitech.lib.android.fireswitch

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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
}
