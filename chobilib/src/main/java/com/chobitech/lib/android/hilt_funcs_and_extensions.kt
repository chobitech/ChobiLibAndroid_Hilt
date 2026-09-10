package com.chobitech.lib.android

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel

@Composable
inline fun <reified VM: ViewModel> hiltViewModelWithActivity(): VM {
    val act = LocalActivity.current as? ComponentActivity ?: throw IllegalStateException("Activity is missing or not a ComponentActivity")
    return hiltViewModel(act)
}
