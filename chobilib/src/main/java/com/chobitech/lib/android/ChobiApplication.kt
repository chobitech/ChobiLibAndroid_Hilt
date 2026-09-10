package com.chobitech.lib.android

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class ChobiApplication {

    val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

}
