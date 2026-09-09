package com.chobitech.lib.android.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import com.chobitech.lib.android.FireSwitch

@Composable
fun rememberFireSwitchForSimpleAlertDialog(
    openSwitch: FireSwitch? = null,
    closeSwitch: FireSwitch? = null,
    allowCloseOnTapOutside: Boolean = true,
    title: @Composable (() -> Unit)? = null,
    dialogContent: @Composable (closeSwitch: FireSwitch) -> Unit,
    confirmButton: @Composable (closeSwitch: FireSwitch) -> Unit,
    dismissButton: @Composable ((closeSwitch: FireSwitch) -> Unit)? = null,
): Pair<FireSwitch, FireSwitch> {

    var showDialog by remember { mutableStateOf(false) }

    val innerAllowCloseOnTapOutside by rememberUpdatedState(allowCloseOnTapOutside)

    val innerOnDismiss = remember {
        {
            if (innerAllowCloseOnTapOutside) {
                showDialog = false
            }
        }
    }

    val innerOpenDialog = remember {
        {
            showDialog = true
        }
    }

    val innerCloseDialog = remember {
        {
            showDialog = false
        }
    }

    val currentOpenSwitch = rememberFireSwitch(
        outerFierSwitch = openSwitch,
        onFired = innerOpenDialog
    )

    val currentCloseSwitch = rememberFireSwitch(
        outerFierSwitch = closeSwitch,
        onFired = innerCloseDialog
    )

    val currentDialogContent by rememberUpdatedState(dialogContent)
    val innerDialogText = remember {
        @Composable {
            currentDialogContent(currentCloseSwitch)
        }
    }

    val currentConfirmButton by rememberUpdatedState(confirmButton)
    val innerConfirmButton = remember {
        @Composable {
            currentConfirmButton(currentCloseSwitch)
        }
    }

    val innerDismissButton = remember(dismissButton) {
        if (dismissButton == null) {
            null
        } else {
            @Composable {
                dismissButton(currentCloseSwitch)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = innerOnDismiss,
            title = title,
            text = innerDialogText,
            confirmButton = innerConfirmButton,
            dismissButton = innerDismissButton
        )
    }

    return remember(currentOpenSwitch, currentCloseSwitch) {
        Pair(currentOpenSwitch, currentCloseSwitch)
    }
}