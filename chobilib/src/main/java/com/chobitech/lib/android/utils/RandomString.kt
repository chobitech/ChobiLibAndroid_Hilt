package com.chobitech.lib.android.utils

class RandomString(
    seeds: List<String>
) : Randomize<String>(seeds) {

    companion object {
        private const val DIGITS = "0123456789"
        private const val HEX_CHARS = "abcdef"
        private const val NON_HEX_CHARS = "ghijklmnopqrstuvwxyz"

        val digitOnly by lazy { RandomString(DIGITS) }

        val lowerHex by lazy { RandomString(DIGITS + HEX_CHARS) }
        val upperHex by lazy { RandomString(DIGITS + HEX_CHARS.uppercase()) }
        val mixedHex by lazy { RandomString(DIGITS + HEX_CHARS + HEX_CHARS.uppercase()) }

        val lowerChars by lazy { RandomString(HEX_CHARS + NON_HEX_CHARS) }
        val upperChars by lazy { RandomString((HEX_CHARS + NON_HEX_CHARS).uppercase()) }
        val chars by lazy {
            val allChars = HEX_CHARS + NON_HEX_CHARS
            RandomString(allChars + allChars.uppercase())
        }

        val lowerCharsAndDigits by lazy { RandomString(DIGITS + HEX_CHARS + NON_HEX_CHARS) }
        val upperCharsAndDigits by lazy { RandomString(DIGITS + (HEX_CHARS + NON_HEX_CHARS).uppercase()) }
        val charsAndDigits by lazy {
            val allChars = HEX_CHARS + NON_HEX_CHARS
            RandomString(DIGITS + allChars + allChars.uppercase())
        }
    }

    constructor(seedString: String) : this(seedString.toCharArray().map { it.toString() })

    fun getRandomString(size: Int, allowDuplication: Boolean = true): String =
        StringBuilder().also { sb ->
            getRandomValues(size, allowDuplication).forEach { s ->
                sb.append(s)
            }
        }.toString()

}