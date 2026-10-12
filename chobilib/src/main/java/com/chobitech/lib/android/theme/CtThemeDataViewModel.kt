package com.chobitech.lib.android.theme

import androidx.lifecycle.ViewModel

open class CtThemeDataViewModel<T : CtThemeData>(
    val themeDataManager: CtThemeDataManager<T>
) : ViewModel() {



}