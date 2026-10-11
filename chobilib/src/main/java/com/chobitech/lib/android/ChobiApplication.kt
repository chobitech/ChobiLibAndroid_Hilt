package com.chobitech.lib.android

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class ChobiApplication : Application() {

    val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _isInitialized = MutableStateFlow(false)
    val isInitializedFlow = _isInitialized.asStateFlow()

    open val debugLogTag: String? = null

    protected open suspend fun innerInitApp() {

    }

    private suspend fun initApp() {
        innerInitApp()
        _isInitialized.value = true
    }

    override fun onCreate() {
        super.onCreate()

        debugLogTag?.also {
            DebugLog.tag = it
        }

        appScope.launch {
            initApp()
        }
    }

}
