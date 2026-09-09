package com.chobitech.lib.android

import java.security.SecureRandom

object ChobiLib {

    val sharedSecureRandom by lazy { SecureRandom() }

}