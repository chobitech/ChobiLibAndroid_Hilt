package com.chobitech.lib.android.contentpage

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource


abstract class NavContentPage(

) {
    open val enableContentScroll: Boolean = true

    @Composable
    abstract fun InnerContent(
        args: Bundle?,
        contentScrollState: ScrollState
    )

    @Composable
    open fun Content(
        args: Bundle?,
        contentScrollState: ScrollState = rememberScrollState()
    ) {
        val modif = Modifier

        if (enableContentScroll) {
            modif.fillMaxSize()
                .verticalScroll(contentScrollState)
        }

        Column(
            modifier = modif
        ) {

            InnerContent(
                args,
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


    open fun isMatchRoute(route: String): Boolean {
        //return (route == this.route) || routePrefixRegex.containsMatchIn(route)
        return (route == this.route)
                || route.startsWith("${this.route}?")
                || route.startsWith("${uriScheme}://${this.route}")
    }

}