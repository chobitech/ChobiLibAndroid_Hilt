package com.chobitech.lib.android

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun getFractionalWidth(fraction: Float): Dp {
    val widthInPx = LocalWindowInfo.current.containerSize.width
    val density = LocalDensity.current
    return with(density) { (widthInPx * fraction).toDp() }
}

@Composable
fun getFractionalHeight(fraction: Float): Dp {
    val heightInPx = LocalWindowInfo.current.containerSize.height
    val density = LocalDensity.current
    return with(density) { (heightInPx * fraction).toDp() }
}


inline fun <reified T : ViewModel> ComponentActivity.lazyViewModel(crossinline factory: () -> T): Lazy<T> {
    return viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <S : ViewModel> create(modelClass: Class<S>): S {
                return factory() as S
            }
        }
    }
}


fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}


fun Context.findComponentActivity(): ComponentActivity? = findActivity() as? ComponentActivity



private val defaultTg by lazy { Typography() }

fun createTypoGraphy(fontFamily: FontFamily): Typography =
    Typography(
        displayLarge = defaultTg.displayLarge.copy(fontFamily = fontFamily),
        displayMedium = defaultTg.displayMedium.copy(fontFamily = fontFamily),
        displaySmall = defaultTg.displaySmall.copy(fontFamily = fontFamily),

        headlineLarge = defaultTg.headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = defaultTg.headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = defaultTg.headlineSmall.copy(fontFamily = fontFamily),

        titleLarge = defaultTg.titleLarge.copy(fontFamily = fontFamily),
        titleMedium = defaultTg.titleMedium.copy(fontFamily = fontFamily),
        titleSmall = defaultTg.titleSmall.copy(fontFamily = fontFamily),

        bodyLarge = defaultTg.bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = defaultTg.bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = defaultTg.bodySmall.copy(fontFamily = fontFamily),

        labelLarge = defaultTg.labelLarge.copy(fontFamily = fontFamily),
        labelMedium = defaultTg.labelMedium.copy(fontFamily = fontFamily),
        labelSmall = defaultTg.labelSmall.copy(fontFamily = fontFamily)
    )


fun Context.isPermissionOk(permission: String): Boolean =
    checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

fun Context.isPermissionsOk(permissions: Array<String>): Map<String, Boolean> =
    hashMapOf<String, Boolean>().also { res ->
        permissions.forEach { p ->
            res[p] = isPermissionOk(p)
        }
    }


val Activity.isAvailable: Boolean
    get() = !isFinishing && !isDestroyed


fun <T> Flow<T>.toStateFlow(
    scope: CoroutineScope,
    initVal: T,
    stopTimeoutMs: Long = 5000
): StateFlow<T> = this.stateIn(
    scope = scope,
    started = SharingStarted.WhileSubscribed(stopTimeoutMs),
    initialValue = initVal
)

fun <S, T> Flow<T>.toMappedStateFlow(
    scope: CoroutineScope,
    initVal: S,
    mapper: ((T) -> S),
    stopTimeoutMs: Long = 5000
): StateFlow<S> {
    val flow = this.map(mapper).distinctUntilChanged()
    return flow.toStateFlow(scope, initVal, stopTimeoutMs)
}

fun <T> ViewModel.createStateFlow(flow: Flow<T>, initVal: T, stopTimeoutMs: Long = 5000) = flow.toStateFlow(
    scope = this.viewModelScope,
    initVal = initVal,
    stopTimeoutMs = stopTimeoutMs
)

fun <S, T> ViewModel.createMappedStateFlow(
    flow: Flow<T>,
    initVal: S,
    mapper: (T) -> S,
    stopTimeoutMs: Long = 5000
) = flow.toMappedStateFlow(this.viewModelScope, initVal, mapper, stopTimeoutMs)
