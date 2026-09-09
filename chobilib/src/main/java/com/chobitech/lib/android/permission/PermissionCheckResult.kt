package com.chobitech.lib.android.permission

enum class PermissionCheckResult(val value: Int) {
    GRANTED(0),
    DENIED(1),
    PERMANENTLY_DENIED(2),
    UNCHECKED(-1);

    companion object {
        fun from(value: Int): PermissionCheckResult = entries.find { it.value == value } ?: UNCHECKED
    }
}

