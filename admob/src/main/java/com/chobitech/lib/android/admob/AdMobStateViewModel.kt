package com.chobitech.lib.android.admob

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.chobitech.lib.android.createStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdMobStateViewModel @Inject constructor(
    private val adMobStateManager: AdMobStateManager
) : ViewModel() {

    val isAdLoadOK = createStateFlow(adMobStateManager.isAdLoadOkFlow, false)
    val isInitialized = createStateFlow(adMobStateManager.isInitializedFlow, false)

    fun startAdMobInitialize(
        appId: String,
        activity: Activity,
        isDebug: Boolean,
        resetUmpState: Boolean
    ) = adMobStateManager.startInitialize(appId, activity, isDebug, resetUmpState)

}