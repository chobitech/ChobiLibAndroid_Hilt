package com.chobitech.lib.android.composable.nav

import androidx.navigation.NavType

data class ContentPageArgs<T>(
    val name: String,
    val type: NavType<T>,
    val defVal: T
)
