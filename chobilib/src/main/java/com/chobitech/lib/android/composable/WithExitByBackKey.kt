package com.chobitech.lib.android.composable

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.chobitech.lib.android.R

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun WithExitByBackKey(
    enableBackKey: Boolean,
    exitTimeoutMs: Long = 2000,
    toastMessage: String? = null,
    onDisableBackKey: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    var lastBackKeyPressedTimeMs by remember { mutableLongStateOf(0L) }
    val context = LocalContext.current
    val activity = LocalActivity.current

    val tMsg = remember(toastMessage) {
        toastMessage ?: context.getString(R.string.msg_press_back_to_exit)
    }



    BackHandler {
        when (!enableBackKey) {
            true -> onDisableBackKey?.invoke()
            false -> {
                val curMs = System.currentTimeMillis()
                val elapsedMs = curMs - lastBackKeyPressedTimeMs

                when (elapsedMs <= exitTimeoutMs) {
                    true -> activity?.finish()
                    false -> {
                        lastBackKeyPressedTimeMs = curMs
                        Toast.makeText(
                            context.applicationContext,
                            tMsg,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    content()
}