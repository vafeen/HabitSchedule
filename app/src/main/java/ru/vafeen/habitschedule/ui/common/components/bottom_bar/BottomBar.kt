package ru.vafeen.habitschedule.ui.common.components.bottom_bar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.vafeen.habitschedule.ui.screens.Screens

@Composable
fun BottomBar(
    selected1: Boolean = false,
    selected2: Boolean = false,
    onClickToScreen1: () -> Unit = {},
    onClickToScreen2: () -> Unit = {},
) {
    val sizeOfIcons = 30.dp
    BottomAppBar(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)) {


        NavigationBarItem(
            selected = selected1,
            onClick = onClickToScreen1,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home, contentDescription = "home screen",
                    modifier = Modifier.size(sizeOfIcons)
                )
            })

        NavigationBarItem(
            selected = selected2,
            onClick = onClickToScreen2,
            icon = {
                Icon(
                    imageVector = Icons.Default.List, contentDescription = "data screen",
                    modifier = Modifier.size(sizeOfIcons)
                )
            })
    }
}