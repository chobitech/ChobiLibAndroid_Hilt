package com.chobitech.lib.android.permission

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.chobitech.lib.android.FireSwitch
import com.chobitech.lib.android.composable.rememberFireSwitchForPermissionCheck


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