package com.chobitech.lib.android.contentpage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

fun PaddingValues.copy(
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    start: Dp = this.calculateStartPadding(layoutDirection),
    end: Dp = this.calculateEndPadding(layoutDirection),
    top: Dp = this.calculateTopPadding(),
    bottom: Dp = this.calculateBottomPadding()
) : PaddingValues {
    return PaddingValues(
        start = start,
        end = end,
        top = top,
        bottom = bottom
    )
}