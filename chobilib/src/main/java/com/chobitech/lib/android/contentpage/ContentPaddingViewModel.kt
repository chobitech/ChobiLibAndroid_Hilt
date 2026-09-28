package com.chobitech.lib.android.contentpage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.chobitech.lib.android.createStateFlow

open class ContentPaddingViewModel(
    private val contentPaddingManager: ContentPaddingManager
) : ViewModel() {

    val contentLayoutDirectionFlow = createStateFlow(
        contentPaddingManager.contentLayoutDirectionFlow,
        initVal = LayoutDirection.Ltr
    )
    fun setContentLayoutDirection(layoutDirection: LayoutDirection) =
        contentPaddingManager.setContentLayoutDirection(layoutDirection)

    val contentPaddingFlow = createStateFlow(
        contentPaddingManager.contentPaddingFlow,
        initVal = PaddingValues.Zero
    )
    val contentTopPaddingFlow = createStateFlow(
        contentPaddingManager.contentTopPaddingFlow,
        initVal = 0.dp
    )
    val contentBottomPaddingFlow = createStateFlow(
        contentPaddingManager.contentBottomPaddingFlow,
        initVal = 0.dp
    )

    val contentStartPaddingFlow = createStateFlow(
        contentPaddingManager.contentStartPaddingFlow,
        initVal = 0.dp
    )
    val contentEndPaddingFlow = createStateFlow(
        contentPaddingManager.contentEndPaddingFlow,
        initVal = 0.dp
    )

    fun setContentPadding(padding: PaddingValues) =
        contentPaddingManager.setContentPadding(padding)

    fun setContentTopPadding(topPadding: Dp) =
        contentPaddingManager.setContentTopPadding(topPadding)

    fun setContentBottomPadding(bottomPadding: Dp) =
        contentPaddingManager.setContentBottomPadding(bottomPadding)

    fun setContentStartPadding(startPadding: Dp) =
        contentPaddingManager.setContentStartPadding(startPadding)

    fun setContentEndPadding(endPadding: Dp) =
        contentPaddingManager.setContentEndPadding(endPadding)


    val scaffoldLayoutDirectionFlow = createStateFlow(
        contentPaddingManager.scaffoldLayoutDirectionFlow,
        initVal = LayoutDirection.Ltr
    )
    fun setScaffoldLayoutDirection(layoutDirection: LayoutDirection) =
        contentPaddingManager.setScaffoldLayoutDirection(layoutDirection)

    val scaffoldPaddingFlow = createStateFlow(
        contentPaddingManager.scaffoldPaddingFlow,
        initVal = PaddingValues.Zero
    )
    val scaffoldTopPaddingFlow = createStateFlow(
        contentPaddingManager.scaffoldTopPaddingFlow,
        initVal = 0.dp
    )
    val scaffoldBottomPaddingFlow = createStateFlow(
        contentPaddingManager.scaffoldBottomPaddingFlow,
        initVal = 0.dp
    )
    val scaffoldStartPaddingFlow = createStateFlow(
        contentPaddingManager.scaffoldStartPaddingFlow,
        initVal = 0.dp
    )
    val scaffoldEndPaddingFlow = createStateFlow(
        contentPaddingManager.scaffoldEndPaddingFlow,
        initVal = 0.dp
    )

    fun setScaffoldPadding(padding: PaddingValues) =
        contentPaddingManager.setScaffoldPadding(padding)

    fun setScaffoldTopPadding(topPadding: Dp) =
        contentPaddingManager.setScaffoldTopPadding(topPadding)

    fun setScaffoldBottomPadding(bottomPadding: Dp) =
        contentPaddingManager.setScaffoldBottomPadding(bottomPadding)

    fun setScaffoldStartPadding(startPadding: Dp) =
        contentPaddingManager.setScaffoldStartPadding(startPadding)

    fun setScaffoldEndPadding(endPadding: Dp) =
        contentPaddingManager.setScaffoldEndPadding(endPadding)




    open val totalPaddingFlow = createStateFlow(
        contentPaddingManager.totalPaddingFlow,
        initVal = PaddingValues.Zero
    )

    open val totalTopPaddingFlow = createStateFlow(
        contentPaddingManager.totalTopPaddingFlow,
        initVal = 0.dp
    )

    open val totalBottomPaddingFlow = createStateFlow(
        contentPaddingManager.totalBottomPaddingFlow,
        initVal = 0.dp
    )

    open val totalStartPaddingFlow = createStateFlow(
        contentPaddingManager.totalStartPaddingFlow,
        initVal = 0.dp
    )

    open val totalEndPaddingFlow = createStateFlow(
        contentPaddingManager.totalEndPaddingFlow,
        initVal = 0.dp
    )

}