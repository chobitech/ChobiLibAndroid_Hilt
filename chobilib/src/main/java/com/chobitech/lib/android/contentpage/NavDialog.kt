package com.chobitech.lib.android.contentpage

import androidx.compose.runtime.Composable

abstract class NavDialog {

    abstract val route: String

    @Composable
    abstract fun DialogContent()

}