package com.chobitech.lib.android.theme

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chobitech.lib.android.contentpage.ContentPaddingViewModel


@Composable
fun <T : CtThemeData, M : CtThemeDataManager<T>> CtTheme(
    themeDataManager: M,
    contentPaddingViewModel: ContentPaddingViewModel? = null,
    typography: Typography = MaterialTheme.typography,
    content: @Composable () -> Unit,
) {
    val activity = LocalActivity.current

    val themeData by themeDataManager.currentThemeDataFlow.collectAsStateWithLifecycle()

    LaunchedEffect(themeData) {

        themeData.changeAppNightMode()

        activity?.also { act ->
            val useLightColor = !themeData.isDarkTheme
            val window = act.window
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = useLightColor
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = useLightColor
        }
    }

    MaterialTheme(
        colorScheme = themeData.colorScheme,
        typography = typography
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { padding ->
            contentPaddingViewModel?.setScaffoldPadding(padding)
            content()
        }
    }
}

@Composable
fun <T : CtThemeData, M : CtThemeDataManager<T>, VM: CtThemeDataViewModel<T, M>> CtTheme(
    themeViewModel: VM,
    contentPaddingViewModel: ContentPaddingViewModel? = null,
    typography: Typography = MaterialTheme.typography,
    content: @Composable () -> Unit,
) = CtTheme(
    themeDataManager = themeViewModel.themeManager,
    contentPaddingViewModel = contentPaddingViewModel,
    typography = typography,
    content = content
)
