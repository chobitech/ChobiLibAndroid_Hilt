package com.chobitech.lib.android.composable

import android.content.Context
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.chobitech.lib.android.FireSwitch

@Composable
fun <I, O> rememberFireSwitchForActivityResultLauncher(
    outerFireSwitch: FireSwitch? = null,
    argsGetter: () -> I,
    contractGenerator: () -> ActivityResultContract<I, O>,
    onResult: (context: Context, result: O) -> Unit
): FireSwitch {
    val currentLauncher = rememberActivityResultLauncher(
        contractGenerator = contractGenerator,
        onResult = onResult
    )

    val currentArgsGetter by rememberUpdatedState(argsGetter)

    val currentOnFired = remember(currentLauncher) {
        {
            currentLauncher.launch(currentArgsGetter())
        }
    }

    val currentFireSwitch = rememberFireSwitch(
        outerFierSwitch = outerFireSwitch,
        onFired = currentOnFired
    )

    return currentFireSwitch
}
