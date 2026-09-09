package com.chobitech.lib.android.permission

import android.content.Context
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.chobitech.lib.android.FireSwitch
import com.chobitech.lib.android.composable.WithActivityResult
import com.chobitech.lib.android.composable.rememberFireSwitchForPermissionCheck
import com.chobitech.lib.android.findActivity


@Composable
fun WithPermissionCheck(
    permissions: Array<String>,
    fireSwitch: FireSwitch? = null,
    onResult: (context: Context, Map<String, PermissionCheckResult>) -> Unit,
    content: @Composable (fireSwitch: FireSwitch) -> Unit
) {
    val currentFireSwitch = remember(fireSwitch) {
        fireSwitch ?: FireSwitch()
    }

    val launchFireSwitch = rememberFireSwitchForPermissionCheck(
        outerFireSwitch = currentFireSwitch,
        permissions = permissions,
        onResult = onResult
    )

    content(launchFireSwitch)
}