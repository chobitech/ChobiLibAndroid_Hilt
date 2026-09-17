package com.chobitech.lib.android.composable.nav

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

open class NavContentPage(
    val route: String,
    @StringRes val labelRes: Int? = null,
    val icon: ImageVector? = null,
    val argsList: List<NavContentPageArgs<*>>? = null,
    val uriScheme: String = DEFAULT_URI_SCHEME,
    val content: @Composable (args: Bundle?) -> Unit
) {

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

    fun getLabelString(context: Context) = labelRes?.let { context.getString(it) }

    val labelString @Composable get() = labelRes?.let { stringResource(it) }

}