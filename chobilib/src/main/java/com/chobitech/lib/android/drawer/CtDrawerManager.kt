package com.chobitech.lib.android.drawer

import com.chobitech.lib.android.fireswitch.FireSwitch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class CtDrawerManager {

    val openFireSwitch = FireSwitch()
    fun open() = openFireSwitch.fire()

    val closeFireSwitch = FireSwitch()
    fun close() = closeFireSwitch.fire()

    private val _isDrawerOpened = MutableStateFlow(false)
    val isDrawerOpenedFlow = _isDrawerOpened.asStateFlow()

    fun setIsDrawerOpened(opened: Boolean) {
        _isDrawerOpened.asStateFlow()
    }

}
