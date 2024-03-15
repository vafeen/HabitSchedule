package ru.vafeen.habitschedule.ui.common.components.bottom_bar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ru.vafeen.habitschedule.ui.screens.Screens

@Composable
fun BottomBar(
    selected1: Boolean = false,
    selected2: Boolean = false,
    onClickToScreen1: () -> Unit = {},
    onClickToScreen2: () -> Unit = {},
) {
    BottomAppBar(modifier = Modifier.fillMaxSize()) {

        NavigationBarItem(
            selected = selected1,
            onClick =  onClickToScreen1,
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "home screen") })

        NavigationBarItem(
            selected = selected2,
            onClick = onClickToScreen2,
            icon = { Icon(imageVector = Icons.Default.List, contentDescription = "data screen") })
    }
}