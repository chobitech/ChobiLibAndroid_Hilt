package com.chobitech.lib.android.contentpage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.plus
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

open class ContentPaddingManager {

    private val _contentPaddingManager = PaddingManager()

    val contentLayoutDirectionFlow = _contentPaddingManager.layoutDirectionFlow
    fun setContentLayoutDirection(layoutDirection: LayoutDirection) =
        _contentPaddingManager.setLayoutDirection(layoutDirection)

    val contentPaddingFlow = _contentPaddingManager.paddingFlow
    val contentTopPaddingFlow = _contentPaddingManager.topPaddingFlow
    val contentBottomPaddingFlow = _contentPaddingManager.bottomPaddingFlow
    val contentStartPaddingFlow = _contentPaddingManager.startPaddingFlow
    val contentEndPaddingFlow = _contentPaddingManager.endPaddingFlow

    fun setContentPadding(padding: PaddingValues) =
        _contentPaddingManager.setPadding(padding)

    fun setContentTopPadding(topPadding: Dp) =
        _contentPaddingManager.setTopPadding(topPadding)

    fun setContentBottomPadding(bottomPadding: Dp) =
        _contentPaddingManager.setBottomPadding(bottomPadding)

    fun setContentStartPadding(startPadding: Dp) =
        _contentPaddingManager.setStartPadding(startPadding)

    fun setContentEndPadding(endPadding: Dp) =
        _contentPaddingManager.setEndPadding(endPadding)



    private val _scaffoldPaddingManager = PaddingManager()

    val scaffoldLayoutDirectionFlow = _scaffoldPaddingManager.layoutDirectionFlow
    fun setScaffoldLayoutDirection(layoutDirection: LayoutDirection) =
        _scaffoldPaddingManager.setLayoutDirection(layoutDirection)

    val scaffoldPaddingFlow = _scaffoldPaddingManager.paddingFlow
    val scaffoldTopPaddingFlow = _scaffoldPaddingManager.topPaddingFlow
    val scaffoldBottomPaddingFlow = _scaffoldPaddingManager.bottomPaddingFlow
    val scaffoldStartPaddingFlow = _scaffoldPaddingManager.startPaddingFlow
    val scaffoldEndPaddingFlow = _scaffoldPaddingManager.endPaddingFlow

    fun setScaffoldPadding(padding: PaddingValues) =
        _scaffoldPaddingManager.setPadding(padding)

    fun setScaffoldTopPadding(topPadding: Dp) =
        _scaffoldPaddingManager.setTopPadding(topPadding)

    fun setScaffoldBottomPadding(bottomPadding: Dp) =
        _scaffoldPaddingManager.setBottomPadding(bottomPadding)

    fun setScaffoldStartPadding(startPadding: Dp) =
        _scaffoldPaddingManager.setStartPadding(startPadding)

    fun setScaffoldEndPadding(endPadding: Dp) =
        _scaffoldPaddingManager.setEndPadding(endPadding)




    open val totalPaddingFlow = combine(
        contentPaddingFlow,
        scaffoldPaddingFlow
    ) { cPadding, sPadding ->
        cPadding + sPadding
    }
        .distinctUntilChanged()

    open val totalTopPaddingFlow = combine(
        contentTopPaddingFlow,
        scaffoldTopPaddingFlow
    ) { c, s ->
        c + s
    }
        .distinctUntilChanged()

    open val totalBottomPaddingFlow = combine(
        contentBottomPaddingFlow,
        scaffoldBottomPaddingFlow
    ) { c, s ->
        c + s
    }
        .distinctUntilChanged()

    open val totalStartPaddingFlow = combine(
        contentStartPaddingFlow,
        scaffoldStartPaddingFlow
    ) { c, s ->
        c + s
    }
        .distinctUntilChanged()

    open val totalEndPaddingFlow = combine(
        contentEndPaddingFlow,
        scaffoldEndPaddingFlow
    ) { c, s ->
        c + s
    }
        .distinctUntilChanged()

}