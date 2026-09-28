package com.chobitech.lib.android.contentpage

import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavContentTopPaddingViewModel @Inject constructor(
    private val navContentTopPaddingManager: NavContentTopPaddingManager
) : ViewModel() {
    val topPaddingFlow = navContentTopPaddingManager.topPaddingFlow

    fun setTopPadding(topPadding: Dp) = navContentTopPaddingManager.setTopPadding(topPadding)
}