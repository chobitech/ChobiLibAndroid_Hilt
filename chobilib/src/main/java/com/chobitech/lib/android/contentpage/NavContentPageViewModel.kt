package com.chobitech.lib.android.contentpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chobitech.lib.android.createStateFlow
import kotlinx.coroutines.launch

open class NavContentPageViewModel(
    val navContentPageStateManager: NavContentPageStateManager
) : ViewModel()  {

    val rootContents: List<NavRootContentPage> = navContentPageStateManager.rootContents

    val popBackSwitchFlow = navContentPageStateManager.popBackSwitchFlow

    fun execPopBack() {
        viewModelScope.launch {
            navContentPageStateManager.execPopBack()
        }
    }

    fun changeRoute(route: String?) = navContentPageStateManager.changeRoute(route)


    val currentRouteFlow = navContentPageStateManager.currentRouteFlow

    val currentRootContentFlow = createStateFlow(
        navContentPageStateManager.currentRootContentFlow,
        initVal = null
    )

    val currentChildContentFlow = createStateFlow(
        navContentPageStateManager.currentChildContentFlow,
        initVal = null
    )

    val currentContentPageFlow = createStateFlow(
        navContentPageStateManager.currentContentPageFlow,
        initVal = null
    )

    fun getContentPage(route: String) = navContentPageStateManager.getContentPage(route)



    val dialogs: List<NavDialog> = navContentPageStateManager.dialogs

    fun getDialog(route: String) = navContentPageStateManager.getDialog(route)

}