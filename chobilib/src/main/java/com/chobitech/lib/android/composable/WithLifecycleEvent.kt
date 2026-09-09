package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun WithLifecycleEvent(
    onLifecycleChanged: (source: LifecycleOwner, event: Lifecycle.Event) -> Unit,
    content: @Composable () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val currentOnLifecycleChanged by rememberUpdatedState(onLifecycleChanged)

    DisposableEffect(lifecycleOwner) {
        val listener = LifecycleEventObserver { source, event ->
            currentOnLifecycleChanged(source, event)
        }
        lifecycleOwner.lifecycle.addObserver(listener)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(listener)
        }
    }

    content()
}