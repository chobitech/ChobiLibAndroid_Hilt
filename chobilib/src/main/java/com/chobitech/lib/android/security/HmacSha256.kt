package com.chobitech.lib.android.security

import android.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HmacSha256 {

    private const val HASH_ALGO = "HmacSHA256"

    fun getHash(data: ByteArray, key: ByteArray): ByteArray {
        val sKey = SecretKeySpec(key, HASH_ALGO)
        return Mac.getInstance(HASH_ALGO).run {
            init(sKey)
            doFinal(data)
        }
    }

    fun getHash(b64Data: String, b64Key: String): ByteArray {
        val data = Base64.decode(b64Data, Base64.DEFAULT)
        val key = Base64.decode(b64Key, Base64.DEFAULT)
        return getHash(data, key)
    }
}