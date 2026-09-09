package com.chobitech.lib.android.composable

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext


@Composable
fun <I, O> rememberActivityResultLauncher(
    contractGenerator: () -> ActivityResultContract<I, O>,
    onResult: (context: Context, result: O) -> Unit
): ActivityResultLauncher<I> {

    val context = LocalContext.current

    val currentOnResult by rememberUpdatedState(onResult)
    val currentContractGenerator by rememberUpdatedState(contractGenerator)

    val launcher = rememberLauncherForActivityResult(
        contract = currentContractGenerator()
    ) { result ->
        currentOnResult(context, result)
    }

    return launcher
}
