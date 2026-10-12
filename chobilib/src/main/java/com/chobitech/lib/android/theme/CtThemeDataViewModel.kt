package com.chobitech.lib.android.theme

import androidx.lifecycle.ViewModel

open class CtThemeDataViewModel<T : CtThemeData, M : CtThemeDataManager<T>>(
    val themeManager: M
) : ViewModel() {



}