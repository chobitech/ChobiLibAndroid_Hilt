package com.chobitech.lib.android.room

import android.util.Base64
import com.chobitech.lib.android.ChobiLib
import com.chobitech.lib.android.DebugLog
import com.chobitech.lib.android.hex
import com.chobitech.lib.android.utils.HmacSHA256
import java.io.File

@OptIn(ExperimentalUnsignedTypes::class)
class DbKey private constructor(
    val source: ByteArray,
    val offset: UByte = 0u
) {

    companion object {
        private const val UBYTE_MAX = 0xff
        const val DEFAULT_KEY_SIZE = 32


        fun fromBytes(bArr: ByteArray): DbKey? {
            return when (bArr.isNotEmpty()) {
                true -> try {
                    val ofs = bArr[0].toUByte()
                    val subArr = bArr.copyOfRange(1, bArr.size)
                    DbKey(subArr, ofs)
                } catch (e: Exception) {
                    DebugLog.e(e)
                    null
                }

                false -> null
            }
        }

        fun fromFile(filePath: String): DbKey? {
            return try {
                val file = File(filePath)
                if (file.exists() && file.isFile) {
                    fromBytes(file.readBytes())
                } else {
                    null
                }
            } catch (e: Exception) {
                DebugLog.e(e)
                null
            }
        }

        fun fromBase64(b64Str: String): DbKey? {
            return try {
                fromBytes(b64Str.toByteArray())
            } catch (e: Exception) {
                DebugLog.e(e)
                null
            }
        }

        fun createAtRandom(keySize: Int = DEFAULT_KEY_SIZE): DbKey {
            val ofs = ChobiLib.sharedSecureRandom.nextInt(256).toUByte()
            val arr = ByteArray(keySize)
            ChobiLib.sharedSecureRandom.nextBytes(arr)
            val sArr = Base64.encode(arr, Base64.DEFAULT)
            return DbKey(sArr, ofs)
        }

        fun printRandomKeyBytes(keySize: Int = DEFAULT_KEY_SIZE) {
            val key = createAtRandom(keySize)
            key.print()
        }
    }

    private constructor(b64Str: String, offset: UByte = 0u) : this(
        b64Str.toByteArray(),
        offset
    )

    fun getKey(): ByteArray {
        val dest = Base64.decode(source, Base64.DEFAULT)

        val ofsInt = offset.toInt()

        if (offset > 0u) {
            for (i in source.indices) {
                val b = ((ofsInt shl i) % UBYTE_MAX).toUByte()
                dest[i] = (dest[i].toUByte() xor b).toByte()
            }
        }

        return dest
    }

    val b64String: String by lazy {
        val bArr = ByteArray(source.size + 1)
        bArr[0] = offset.toByte()
        System.arraycopy(source, 0, bArr, 1, source.size)
        Base64.encodeToString(bArr, Base64.DEFAULT)
    }


    fun saveToFile(filePath: String): Boolean {
        return try {
            val file = File(filePath)
            file.parentFile?.mkdirs()
            file.writeText(b64String)
            true
        } catch (e: Exception) {
            DebugLog.e(e)
            false
        }
    }

    fun computeHash(data: ByteArray): ByteArray? {
        return HmacSHA256.compute(getKey(), data)
    }


    fun print() {
        DebugLog.i("SOURCE = ${source.joinToString(", ", transform = { "0x${it.hex}" })}")
        DebugLog.i("OFFSET = ${offset.hex}")
    }

}