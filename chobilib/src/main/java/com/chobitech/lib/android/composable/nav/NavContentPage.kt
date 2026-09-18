package com.chobitech.lib.android.composable.nav

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource


abstract class NavContentPage(

) {
    @Composable
    abstract fun Content(args: Bundle?)

    abstract val route: String

    @StringRes
    open val labelRes: Int? = null

    open val icon: ImageVector? = null

    open val argsList: List<NavContentPageArgs<*>>? = null

    open val uriScheme: String = DEFAULT_URI_SCHEME



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

}