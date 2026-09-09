package com.chobitech.lib.android.composable

import android.content.Context
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.chobitech.lib.android.FireSwitch
import com.chobitech.lib.android.findActivity
import com.chobitech.lib.android.permission.PermissionCheckResult


private val permissionCheckActivityResultContracts = {
    ActivityResultContracts.RequestMultiplePermissions()
}

@Composable
fun rememberFireSwitchForPermissionCheck(
    outerFireSwitch: FireSwitch? = null,
    permissions: Array<String>,
    onResult: (context: Context, results: Map<String, PermissionCheckResult>) -> Unit
): FireSwitch {
    val currentOnResult by rememberUpdatedState(onResult)

    val argsGetter = remember(permissions) {
        {
            permissions
        }
    }

    val onActivityResult: (context: Context, result: Map<String, Boolean>) -> Unit =
        remember {
            { ctx, res ->
                val activity = ctx.findActivity() ?: return@remember

                val rMap = res.mapValues { (p, isGranted) ->
                    when (isGranted) {
                        true -> PermissionCheckResult.GRANTED
                        false -> when (activity.shouldShowRequestPermissionRationale(p)) {
                            true -> PermissionCheckResult.DENIED
                            false -> PermissionCheckResult.PERMANENTLY_DENIED
                        }
                    }
                }

                currentOnResult(ctx, rMap)
            }
        }

    return rememberFireSwitchForActivityResultLauncher(
        outerFireSwitch = outerFireSwitch,
        argsGetter = argsGetter,
        contractGenerator = permissionCheckActivityResultContracts,
        onResult = onActivityResult
    )
}