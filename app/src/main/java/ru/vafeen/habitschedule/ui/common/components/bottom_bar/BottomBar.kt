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
    navHostController: NavHostController,
    selected1: Boolean = false,
    selected2: Boolean = false,
) {
    BottomAppBar(modifier = Modifier.fillMaxSize()) {

        NavigationBarItem(
            selected = selected1,
            onClick = { navHostController.navigate(Screens.Main.route) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "home screen") })

        NavigationBarItem(
            selected = selected2,
            onClick = { navHostController.navigate(Screens.Data.route) },
            icon = { Icon(imageVector = Icons.Default.List, contentDescription = "data screen") })
    }
}