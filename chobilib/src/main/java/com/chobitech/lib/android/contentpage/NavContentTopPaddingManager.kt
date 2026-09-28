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

    private val _scaffoldTopPadding = MutableStateFlow(0.dp)
    val scaffoldTopPaddingFlow = _scaffoldTopPadding.asStateFlow()

    private val _totalTopPadding = MutableStateFlow(0.dp)
    val totalTopPaddingFlow = _totalTopPadding.asStateFlow()

    fun setScaffoldTopPadding(topPadding: Dp) {
        _scaffoldTopPadding.value = topPadding

        _totalTopPadding.value = topPadding + _topPadding.value
    }
    fun setTopPadding(topPadding: Dp) {
        _topPadding.value = topPadding

        _totalTopPadding.value = topPadding + _scaffoldTopPadding.value
    }


}