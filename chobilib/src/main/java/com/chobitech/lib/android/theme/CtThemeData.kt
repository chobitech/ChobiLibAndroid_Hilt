package com.chobitech.lib.android.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

open class CtThemeData(
    val name: String,
    val isDarkTheme: Boolean,
    val colorScheme: ColorScheme,
) {

    companion object {

        val defaultLight = CtThemeData(
            name = "Default Light",
            isDarkTheme = false,
            colorScheme = lightColorScheme()
        )

        val defaultDark = CtThemeData(
            name = "Default Dark",
            isDarkTheme = true,
            colorScheme = darkColorScheme()
        )

    }


    fun changeAppNightMode() {
        val mode = if (isDarkTheme) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }

}