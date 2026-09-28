//package com.chobitech.lib.android.contentpage
//
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.combine
//import kotlinx.coroutines.flow.distinctUntilChanged
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class NavContentTopPaddingManager @Inject constructor(
//
//) {
//    private val _topPadding = MutableStateFlow(0.dp)
//    val topPaddingFlow = _topPadding.asStateFlow()
//
//    private val _scaffoldTopPadding = MutableStateFlow(0.dp)
//    val scaffoldTopPaddingFlow = _scaffoldTopPadding.asStateFlow()
//
//    val totalTopPaddingFlow: Flow<Dp> = combine(
//        topPaddingFlow,
//        scaffoldTopPaddingFlow
//    ) { top, scaf ->
//        top + scaf
//    }
//        .distinctUntilChanged()
//
//    fun setScaffoldTopPadding(topPadding: Dp) {
//        _scaffoldTopPadding.value = topPadding
//    }
//    fun setTopPadding(topPadding: Dp) {
//        _topPadding.value = topPadding
//    }
//
//
//}