package com.chobitech.lib.android.contentpage

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.chobitech.lib.android.createStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NavContentTopPaddingViewModel @Inject constructor(
    private val navContentTopPaddingManager: NavContentTopPaddingManager
) : ViewModel() {
    val topPaddingFlow = navContentTopPaddingManager.topPaddingFlow

    fun setTopPadding(topPadding: Dp) = navContentTopPaddingManager.setTopPadding(topPadding)

    val scaffoldTopPaddingFlow = navContentTopPaddingManager.scaffoldTopPaddingFlow
    fun setScaffoldTopPadding(topPadding: Dp) = navContentTopPaddingManager.setScaffoldTopPadding(topPadding)


    val totalTopPaddingFlow = createStateFlow(
        navContentTopPaddingManager.totalTopPaddingFlow,
        initVal = 0.dp
    )
}