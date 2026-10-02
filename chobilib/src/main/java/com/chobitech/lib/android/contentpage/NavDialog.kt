package com.chobitech.lib.android.contentpage

import android.os.Bundle
import androidx.compose.runtime.Composable

abstract class NavDialog {

    abstract val route: String

    @Composable
    abstract fun DialogContent(args: Bundle?)

}