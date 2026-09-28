package com.chobitech.lib.android.contentpage

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavContentTopPaddingManager @Inject constructor(

) {

    private val _topPadding = MutableStateFlow(0.dp)
    val topPaddingFlow = _topPadding.asStateFlow()

    fun setTopPadding(topPadding: Dp) {
        _topPadding.value = topPadding
    }

}