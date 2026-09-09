package com.chobitech.lib.android.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


fun Context.checkPermissions(permissions: Array<String>): Map<String, PermissionCheckResult> = hashMapOf<String, PermissionCheckResult>()
    .also { res ->
        permissions.forEach { p ->
            res[p] = when (ContextCompat.checkSelfPermission(this, p)) {
                PackageManager.PERMISSION_GRANTED -> PermissionCheckResult.GRANTED
                else -> PermissionCheckResult.DENIED
            }
        }
    }