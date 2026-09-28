package com.chobitech.lib.android.contentpage

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

open class NavContentPageStateManager {

    private val _popBackSwitch = MutableSharedFlow<Unit>()
    val popBackSwitchFlow = _popBackSwitch.asSharedFlow()
    suspend fun execPopBack() {
        _popBackSwitch.emit(Unit)
    }

    open val rootContents: List<NavRootContentPage> = listOf()


    open val startupPageRoute: String = ""

    private val _currentRoute = MutableStateFlow<String?>(null)


    fun changeRoute(route: String?) {
        _currentRoute.value = route
    }

    val currentContentPageFlow = _currentRoute
        .map { route ->
            route?.let { getContentPage(it) }
        }
        .distinctUntilChanged()


    val currentRouteFlow = _currentRoute.asStateFlow()

    val currentRootContentFlow = _currentRoute
        .map { route ->
            route?.let { rt ->
                rootContents.firstOrNull {
                    it.isMatchRoute(rt)
                }
            }
        }
        .distinctUntilChanged()

    val currentChildContentFlow = _currentRoute
        .map { route ->
            if (route != null) {
                for (rt in rootContents) {
                    val child = rt.getChildContentOrNull(route)
                    if (child != null) {
                        return@map child
                    }
                }
            }
            null
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

}

