package com.chobitech.lib.android.composable

import android.content.Context
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.Composable
import com.chobitech.lib.android.FireSwitch


@Composable
fun <I, O> WithActivityResult(
    fireSwitch: FireSwitch? = null,
    argsGetter: () -> I,
    contractGenerator: () -> ActivityResultContract<I, O>,
    onResult: (context: Context, result: O) -> Unit,
    content: @Composable (fireSwitch: FireSwitch) -> Unit
) {
    val fireSwitch = rememberFireSwitchForActivityResultLauncher(
        outerFireSwitch = fireSwitch,
        argsGetter = argsGetter,
        contractGenerator = contractGenerator,
        onResult = onResult
    )

    content(fireSwitch)
}
