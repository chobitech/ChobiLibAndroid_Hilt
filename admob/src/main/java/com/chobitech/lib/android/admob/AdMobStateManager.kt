package com.chobitech.lib.android.admob

import android.app.Activity
import android.content.Context
import com.chobitech.lib.android.DebugLog
import com.google.android.libraries.ads.mobile.sdk.MobileAds
import com.google.android.libraries.ads.mobile.sdk.initialization.InitializationConfig
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdMobStateManager @Inject constructor(
    @ApplicationContext private val appCon: Context
) {
    private lateinit var consentInfo: ConsentInformation


    private var appId: String? = null

    private val _isInitialized = MutableStateFlow(false)
    val isInitializedFlow = _isInitialized.asStateFlow()

    val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)


    private val _isAdLoadOk = MutableStateFlow(false)
    val isAdLoadOkFlow = _isAdLoadOk.asStateFlow()

    private val _isInitializeExecuted = MutableStateFlow(false)
    val isInitializeExecuted = _isInitializeExecuted.asStateFlow()


    fun startInitialize(
        appId: String,
        activity: Activity,
        isDebug: Boolean,
        resetUmpState: Boolean
    ) {
        this.appId = appId

        if (_isInitialized.value || _isInitializeExecuted.value) {
            return
        }

        startUmpProcess(activity, isDebug, resetUmpState)
    }


    private fun startUmpProcess(
        activity: Activity,
        isDebug: Boolean,
        resetUmpState: Boolean
    ) {
        if (!::consentInfo.isInitialized) {
            consentInfo = UserMessagingPlatform.getConsentInformation(appCon)
        }

        if (resetUmpState) {
            consentInfo.reset()
        }

        val paramsBuilder = ConsentRequestParameters.Builder()

        if (isDebug) {
            val debugSettings = ConsentDebugSettings.Builder(appCon)
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)

            paramsBuilder.setConsentDebugSettings(debugSettings.build())
        }

        consentInfo.requestConsentInfoUpdate(
            activity,
            paramsBuilder.build(),
            {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formErr ->
                    if (formErr != null) {
                        DebugLog.w("UMP: ${formErr.errorCode}: ${formErr.message}")
                    }

                    if (consentInfo.canRequestAds()) {
                        initAdMob()
                    } else {
                        _isInitialized.value = true
                    }
                }
            },
            { reqErr ->
                if (consentInfo.canRequestAds()) {
                    initAdMob()
                } else {
                    _isInitialized.value = true
                }
            }
        )
    }

    private fun initAdMob() {
        appId?.also { id ->
            appScope.launch {
                withContext(Dispatchers.IO) {
                    withContext(Dispatchers.IO) {
                        MobileAds.initialize(
                            appCon,
                            InitializationConfig.Builder(id).build()
                        ) {
                            _isInitialized.value = true
                            _isAdLoadOk.value = true
                        }
                    }
                }
            }
        }
    }

}