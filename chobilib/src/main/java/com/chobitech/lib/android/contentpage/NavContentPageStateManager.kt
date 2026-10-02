package com.chobitech.lib.android.contentpage

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan

open class NavContentPageStateManager {

    private val _popBackSwitch = MutableSharedFlow<Unit>()
    val popBackSwitchFlow = _popBackSwitch.asSharedFlow()
    suspend fun execPopBack() {
        _popBackSwitch.emit(Unit)
    }

    open val rootContents: List<NavRootContentPage> = listOf()


    private val _currentRoute = MutableStateFlow<String?>(null)
    val currentRouteFlow = _currentRoute.asStateFlow()

    fun changeRoute(route: String?) {
        _currentRoute.value = route
    }

    val currentContentPageFlow = _currentRoute
        .map { route ->
            route?.let { getContentPage(it) }
        }
        .distinctUntilChanged()

    val currentRootContentFlow = currentContentPageFlow
        .scan<NavContentPage?, NavRootContentPage?>(null) { curPage, newPage ->
            when (val root = newPage as? NavRootContentPage) {
                null -> curPage
                else -> root
            }
        }
        .distinctUntilChanged()


    val currentChildContentFlow = currentContentPageFlow
        .scan<NavContentPage?, NavChildContentPage?>(null) { curPage, newPage ->
            when (val child = newPage as? NavChildContentPage) {
                null -> curPage
                else -> child
            }
        }
        .distinctUntilChanged()



    fun getContentPage(route: String): NavContentPage? {

        for (rPage in rootContents) {
            if (rPage.isMatchRoute(route)) {
                return rPage
            }

            val child = rPage.getChildContentOrNull(route)
            if (child != null) {
                return child
            }
        }

        return null
    }




    open val rootDialogs: List<NavDialog> = listOf()

    fun getRootDialog(route: String): NavDialog? {
        return rootDialogs.firstOrNull { it.route == route }
    }


}

