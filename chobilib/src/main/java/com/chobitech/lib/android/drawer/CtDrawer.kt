package com.chobitech.lib.android.drawer

import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.chobitech.lib.android.fireswitch.LaunchWithFireSwitch
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun CtDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    drawerManager: CtDrawerManager = CtDrawerManager(),
    gestureEnabled: Boolean = true,
    scrimColor: Color = DrawerDefaults.scrimColor,
    content: @Composable (drawerManager: CtDrawerManager, drawerState: DrawerState) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    LaunchedEffect(drawerState, drawerManager) {
        snapshotFlow { drawerState.isOpen }
            .distinctUntilChanged()
            .collect { isOpened ->
                drawerManager.setIsDrawerOpened(isOpened)
            }
    }


    LaunchWithFireSwitch(
        fireSwitch = drawerManager.openFireSwitch
    ) {
        drawerState.open()
    }

    LaunchWithFireSwitch(
        fireSwitch = drawerManager.closeFireSwitch
    ) {
        drawerState.close()
    }


    ModalNavigationDrawer(
        drawerContent = drawerContent,
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = gestureEnabled,
        scrimColor = scrimColor,
    ) {
        content(drawerManager, drawerState)
    }

}