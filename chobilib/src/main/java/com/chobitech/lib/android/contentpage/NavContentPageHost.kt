package com.chobitech.lib.android.contentpage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost

@Composable
fun NavContentPageHost(
    startDestination: String,
    navContentPageViewModel: NavContentPageViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onRouteChanged: ((navCon: NavHostController) -> Unit)? = null
) {
    val currentRoute by navContentPageViewModel.currentRouteFlow.collectAsStateWithLifecycle()

    val currentContentPage by navContentPageViewModel.currentContentPageFlow.collectAsStateWithLifecycle()

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, dest, _ ->
            dest.route?.also { route ->
                val content = navContentPageViewModel.getContentPage(route)
                if (content != currentContentPage) {
                    navContentPageViewModel.changeRoute(content?.route)
                }
            }
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }


    LaunchedEffect(currentRoute) {
        if (currentRoute == null) {
            return@LaunchedEffect
        }

        onRouteChanged?.invoke(navController)
            ?: run {
                navController.navigate(route = currentRoute!!) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
    }

    LaunchedEffect(Unit) {
        navContentPageViewModel.popBackSwitchFlow.collect {
            if((currentContentPage as? NavChildContentPage)?.onBeforeBackToPreviousPage() != false) {
                navController.popBackStack()
            }
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        navContentPageViewModel.rootContents.forEach { rPage ->
            navRootContentPage(rPage)
        }
    }

}