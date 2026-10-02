package com.chobitech.lib.android.contentpage

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation

fun <T : NavContentPage> NavGraphBuilder.navContentPage(
    navContentPage: T,
    contentScrollState: ScrollState? = null
) {
    val argsList = arrayListOf<NamedNavArgument>().also { aList ->
        navContentPage.argsList?.forEach { t ->
            aList.add(
                navArgument(t.name) {
                    type = t.type
                    defaultValue = t.defVal
                }
            )
        }
    }

    composable(
        route = navContentPage.routeWithArgs,
        arguments = argsList,
        deepLinks = listOf(
            navDeepLink { uriPattern = navContentPage.deepLinkUri }
        )
    ) { backStackEntry ->

        val localScrollState = rememberScrollState()

        navContentPage.Content(
            args = backStackEntry.arguments,
            contentScrollState = contentScrollState ?: localScrollState
        )
    }
}

fun <T : NavRootContentPage> NavGraphBuilder.navRootContentPage(
    rootContentPage: T,
    startDestination: String? = null
) {
    navigation(
        startDestination = startDestination ?: rootContentPage.route,
        route = rootContentPage.navigationRouteName,
    ) {
        navContentPage(rootContentPage)

        for (child in rootContentPage.childContents) {
            navContentPage(child)
        }

    }
}


fun <T : NavDialog> NavGraphBuilder.registerDialog(dialog: T) {
    dialog(
        route = dialog.route
    ) {
        dialog.DialogContent()
    }
}


fun NavController.changeRoute(
    route: String,
    isSaveState: Boolean = true,
    isLaunchSingleTop: Boolean = true,
    isRestoreState: Boolean = true,
    idGetter: ((navCon: NavController) -> Int)? = null
) {
    if (this.currentDestination?.route == route) {
        return
    }

    val id = idGetter?.invoke(this) ?: this.graph.findStartDestination().id

    this.navigate(route) {
        popUpTo(id) {
            saveState = isSaveState
        }
        launchSingleTop = isLaunchSingleTop
        restoreState = isRestoreState
    }
}