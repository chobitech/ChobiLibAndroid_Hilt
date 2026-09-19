package com.chobitech.lib.android.utils

import com.chobitech.lib.android.DebugLog
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HmacSHA256 {

    const val ALGORITHM = "HmacSHA256"

    fun compute(key: ByteArray, data: ByteArray): ByteArray? {
        return try {
            val sKeySpec = SecretKeySpec(key, ALGORITHM)
            val mac = Mac.getInstance(ALGORITHM)
            mac.init(sKeySpec)
            mac.doFinal(data)
        } catch (e: Exception) {
            DebugLog.e(e)
            null
        }
    }

}