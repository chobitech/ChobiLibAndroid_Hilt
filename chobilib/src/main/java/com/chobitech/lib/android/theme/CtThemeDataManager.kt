package com.chobitech.lib.android.theme

import android.content.Context
import androidx.activity.SystemBarStyle
import com.chobitech.lib.android.ChobiApplication
import com.chobitech.lib.android.toMappedStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class CtThemeDataManager<T : CtThemeData>(
    context: Context,
    coroutineScope: CoroutineScope?
) {
    val appContext = context.applicationContext

    val scope = coroutineScope
        ?: (appContext as? ChobiApplication)?.appScope
        ?: CoroutineScope(SupervisorJob() + Dispatchers.IO)

    abstract val themeDataList: List<T>
    abstract val initialThemeData: T

    private val _currentThemeData = MutableStateFlow(initialThemeData)
    val currentThemeDataFlow = _currentThemeData.asStateFlow()

    val isDarkThemeFlow = currentThemeDataFlow
        .toMappedStateFlow(
            scope = scope,
            mapper = { it.isDarkTheme },
            initVal = currentThemeDataFlow.value.isDarkTheme
        )

    val colorSchemeFlow = currentThemeDataFlow
        .toMappedStateFlow(
            scope = scope,
            mapper = { it.colorScheme },
            initVal = currentThemeDataFlow.value.colorScheme
        )

    private fun getSystemBarStyle(isDark: Boolean): SystemBarStyle {
        return when (isDark) {
            true -> SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
            false -> SystemBarStyle.light(android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT)
        }
    }

    val systemBarStyleFlow = isDarkThemeFlow
        .toMappedStateFlow(
            scope = scope,
            mapper = { getSystemBarStyle(it) },
            initVal = getSystemBarStyle(isDarkThemeFlow.value)
        )

    protected open fun onThemeDataSet(themeData: T) {}

    fun setThemeData(themeData: T) {
        _currentThemeData.value = themeData
        onThemeDataSet(themeData)
    }

}
