package com.chobitech.lib.android.admob

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.chobitech.lib.android.hiltViewModelWithActivity


@Composable
fun WithAdMob(
    appId: String,
    isDeque: Boolean = true,
    resetUmpState: Boolean = false,
    startInitializeOnLaunch: Boolean = true,
    content: @Composable (Boolean) -> Unit
) {
    val adMobStateViewModel: AdMobStateViewModel = hiltViewModelWithActivity()

    val isInitialized by adMobStateViewModel.isInitialized.collectAsState()
    val isAdLoadOk by adMobStateViewModel.isAdLoadOK.collectAsState()

    val activity = LocalActivity.current

    if (activity != null && startInitializeOnLaunch && !isInitialized) {
        LaunchedEffect(Unit) {
            adMobStateViewModel.startAdMobInitialize(
                appId, activity, isDeque, resetUmpState
            )
        }
    }

    content(isAdLoadOk)
}
