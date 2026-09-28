package com.chobitech.lib.android.contentpage

abstract class NavRootContentPage : NavContentPage() {

    open val childContents: List<NavChildContentPage> = listOf()

    open val navigationRouteName: String
        get() = "${route}_root"


    fun getChildContentOrNull(route: String): NavChildContentPage? {
        return childContents.firstOrNull {
            it.isMatchRoute(route)
        }
    }

}