package com.chobitech.lib.android.admob

object AdMobId {
    const val NATIVE_TEST_ID = "ca-app-pub-3940256099942544/2247696110"
    const val NATIVE_MOVIE_TEST_ID = "ca-app-pub-3940256099942544/1044960115"
    const val ADAPTIVE_BANNER_TEST_ID = "ca-app-pub-3940256099942544/9214589741"

    fun getAdId(isTest: Boolean, releaseId: String, testId: String): String =
        when (isTest) {
            true -> testId
            false -> releaseId
        }


    fun getNativeAdId(isTest: Boolean, releaseId: String) = getAdId(isTest, releaseId, NATIVE_TEST_ID)
    fun getNativeMovieAdId(isTest: Boolean, releaseId: String) = getAdId(isTest, releaseId, NATIVE_MOVIE_TEST_ID)
    fun getAdaptiveBannerId(isTest: Boolean, releaseId: String) = getAdId(isTest, releaseId, ADAPTIVE_BANNER_TEST_ID)
}


