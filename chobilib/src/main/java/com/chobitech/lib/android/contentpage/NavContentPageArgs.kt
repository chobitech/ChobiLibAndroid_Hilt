package com.chobitech.lib.android.contentpage

import androidx.navigation.NavType

data class NavContentPageArgs<T>(
    val name: String,
    val type: NavType<T>,
    val defVal: T
)
