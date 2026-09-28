package com.chobitech.lib.android.contentpage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.chobitech.lib.android.createMappedStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppContentPaddingViewModel @Inject constructor(
    private val appContentPaddingManager: AppContentPaddingManager
) : ViewModel() {

    val contentPaddingFlow = appContentPaddingManager.contentPaddingFlow

    val topPaddingFlow = createMappedStateFlow(
        contentPaddingFlow,
        mapper = { it.calculateTopPadding() },
        initVal = 0.dp
    )

    fun setAppContentPadding(padding: PaddingValues) =
        appContentPaddingManager.setAppContentPadding(padding)

}