package com.chobitech.lib.android.composable.nav

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

fun NavGraphBuilder.navContentPage(
    navContentPage: NavContentPage
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
        route = navContentPage.route,
        arguments = argsList,
        deepLinks = listOf(
            navDeepLink { uriPattern = navContentPage.uri }
        )
    ) { backStackEntry ->
        navContentPage.content(backStackEntry.arguments)
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