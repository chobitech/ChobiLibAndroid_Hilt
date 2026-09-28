//package com.chobitech.lib.android.contentpage
//
//import androidx.compose.foundation.layout.PaddingValues
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class AppContentPaddingManager @Inject constructor(
//
//) {
//    private val _contentPadding = MutableStateFlow(PaddingValues.Zero)
//    val contentPaddingFlow = _contentPadding.asStateFlow()
//
//    fun setAppContentPadding(padding: PaddingValues) {
//        _contentPadding.value = padding
//    }
//
//}