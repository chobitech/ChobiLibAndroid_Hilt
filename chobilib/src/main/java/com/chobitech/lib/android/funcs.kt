package com.chobitech.lib.android

import android.os.Build

fun <T> runWithApiVersion(apiVer: Int, proc: (isGreaterThanEqual: Boolean) -> T): T {
    return proc(Build.VERSION.SDK_INT >= apiVer)
}

