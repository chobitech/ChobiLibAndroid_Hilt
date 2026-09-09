package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import com.chobitech.lib.android.FireSwitch


@Composable
fun WithSimpleAlertDialog(
    openSwitch: FireSwitch? = null,
    closeSwitch: FireSwitch? = null,
    allowOutsideTapClose: Boolean = true,
    title: @Composable (() -> Unit)? = null,
    dialogContent: @Composable ((closeSwitch: FireSwitch) -> Unit),
    confirmButton: @Composable (closeSwitch: FireSwitch) -> Unit,
    dismissButton: @Composable ((closeSwitch: FireSwitch) -> Unit)? = null,
    content: @Composable (openSwitch: FireSwitch, closeSwitch: FireSwitch) -> Unit
) {

    val switches = rememberFireSwitchForSimpleAlertDialog(
        openSwitch = openSwitch,
        closeSwitch = closeSwitch,
        allowCloseOnTapOutside = allowOutsideTapClose,
        title = title,
        dialogContent = dialogContent,
        confirmButton = confirmButton,
        dismissButton = dismissButton
    )

    content(switches.first, switches.second)
}
