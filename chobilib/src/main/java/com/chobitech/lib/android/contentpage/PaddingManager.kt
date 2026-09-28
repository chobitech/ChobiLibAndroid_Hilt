package com.chobitech.lib.android.contentpage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

open class PaddingManager {

    private val _layoutDirection = MutableStateFlow(LayoutDirection.Ltr)
    val layoutDirectionFlow = _layoutDirection.asStateFlow()

    fun setLayoutDirection(layoutDirection: LayoutDirection) {
        _layoutDirection.value = layoutDirection
    }

    private val _padding = MutableStateFlow(PaddingValues.Zero)
    val paddingFlow = _padding.asStateFlow()

    val topPaddingFlow = paddingFlow
        .map {
            it.calculateTopPadding()
        }
        .distinctUntilChanged()

    val bottomPaddingFlow = paddingFlow
        .map {
            it.calculateBottomPadding()
        }
        .distinctUntilChanged()

    val startPaddingFlow = combine(
        paddingFlow,
        layoutDirectionFlow

    ) { padding, dir ->
        padding.calculateStartPadding(dir)
    }
        .distinctUntilChanged()

    val endPaddingFlow = combine(
        paddingFlow,
        layoutDirectionFlow
    ) { padding, dir ->
        padding.calculateEndPadding(dir)
    }
        .distinctUntilChanged()


    private fun updatePadding(updater: (cur: PaddingValues) -> PaddingValues) {
        _padding.value = updater(_padding.value)
    }

    fun setPadding(padding: PaddingValues) =
        updatePadding { padding }

    fun setTopPadding(topPadding: Dp) =
        updatePadding { it.copy(top = topPadding) }

    fun setBottomPadding(bottomPadding: Dp) =
        updatePadding { it.copy(bottom = bottomPadding) }

    fun setStartPadding(startPadding: Dp) =
        updatePadding { it.copy(start = startPadding) }

    fun setEndPadding(endPadding: Dp) =
        updatePadding { it.copy(end = endPadding) }

}