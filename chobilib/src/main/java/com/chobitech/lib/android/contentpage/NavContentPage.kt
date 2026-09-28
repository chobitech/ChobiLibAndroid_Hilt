package com.chobitech.lib.android.contentpage

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import javax.inject.Inject


abstract class NavContentPage(

) {
    open val enableContentScroll: Boolean = true
    open val addTopPaddingToContentTop: Boolean = true

    @Composable
    abstract fun InnerContent(
        args: Bundle?,
        topPadding: Dp,
        contentScrollState: ScrollState
    )

    @Composable
    open fun Content(
        args: Bundle?,
        topPadding: Dp? = null,
        contentScrollState: ScrollState = rememberScrollState()
    ) {
        val topPaddingViewModel: NavContentTopPaddingViewModel = hiltViewModel()
        //val topPaddingByFlow by topPaddingViewModel.topPaddingFlow.collectAsStateWithLifecycle()
        val totalTopPadding by topPaddingViewModel.totalTopPaddingFlow.collectAsStateWithLifecycle()

        val topP = topPadding ?: totalTopPadding

        val modif = Modifier

        if (enableContentScroll) {
            modif.fillMaxSize()
                .verticalScroll(contentScrollState)
        }

        Column(
            modifier = modif
        ) {
            if (addTopPaddingToContentTop && topP > 0.dp) {
                Spacer(
                    modifier = Modifier.height(topP)
                )
            }

            InnerContent(
                args,
                topP,
                contentScrollState
            )
        }


    }


    abstract val route: String

    @StringRes
    open val labelRes: Int? = null

    open val icon: ImageVector? = null

    open val argsList: List<NavContentPageArgs<*>>? = null

    open val uriScheme: String = DEFAULT_URI_SCHEME


    private val routePrefixRegex = "^(?:${uriScheme}://)?$route\\??".toRegex()


    companion object {
        const val DEFAULT_URI_SCHEME = "myNcp"
    }


    val argsString: String? by lazy {
        argsList?.map { it.name }?.joinToString("&", transform = { "$it={$it}" })
    }

    val routeWithArgs by lazy { argsString?.let { "$route?$it" } ?: route }

    open val deepLinkUri: String by lazy {
        "$uriScheme://$routeWithArgs"
    }

    fun getRouteUri(argsMap: Map<String, Any?>, withScheme: Boolean = false): String {
        val uriSb = StringBuilder()

        if (withScheme) {
            uriSb.append(uriScheme).append("://")
        }

        uriSb.append(route)

        if (argsMap.isNotEmpty()) {
            uriSb.append("?")
                .append(
                    argsMap.map { "${it.key}=${it.value}" }.joinToString("&")
                )
        }

        return uriSb.toString()
    }

    fun getLabelString(context: Context) = labelRes?.let { context.getString(it) }

    val labelString @Composable get() = labelRes?.let { stringResource(it) }


    fun isMatchRoute(route: String): Boolean {
        return (route == this.route) || routePrefixRegex.containsMatchIn(route)
    }

}