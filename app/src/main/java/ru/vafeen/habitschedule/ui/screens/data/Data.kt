package ru.vafeen.habitschedule.ui.screens.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.vafeen.habitschedule.main.application.HabitApp
import ru.vafeen.habitschedule.noui.HabitItem
import ru.vafeen.habitschedule.noui.db.DatabaseRepository
import ru.vafeen.habitschedule.noui.log.LogType
import ru.vafeen.habitschedule.noui.log.logExecutor
import ru.vafeen.habitschedule.ui.common.components.bottom_bar.BottomBar
import ru.vafeen.habitschedule.ui.common.components.card_of_habit.CardOfHabit
import ru.vafeen.habitschedule.ui.common.components.dialogs.AddingHabitDialog
import ru.vafeen.habitschedule.ui.screens.Screens
import ru.vafeen.habitschedule.ui.theme.common.HabitScheduleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Data(
    navHostController: NavHostController,
) {
    var db: DatabaseRepository? = null

    val itemDao = HabitApp.hSDB?.habitItemDao()

    val dtDao = HabitApp.hSDB?.habitDateTimeDao()

    if (itemDao != null && dtDao != null) {
        db = DatabaseRepository(
            itemDao = itemDao, dtDao = dtDao
        )
        
    }

    if (db != null) {
        val cor = rememberCoroutineScope()

        var listik by remember {
            mutableStateOf(listOf<HabitItem>())
        }

        val itemsList by remember {
            mutableStateOf(flow {
                emit(db.getAll())
            })
        }

        LaunchedEffect(key1 = null) {
            itemsList.collect {
                listik = it
            }

            logExecutor(suffixTag = LogType.Database.value, message = "обновление в launchedEffect")
        }


        var textSearch by remember {
            mutableStateOf("")
        }
        var isAddingHabitDialogOpen by remember {
            mutableStateOf(false)
        }


        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Все напоминания", color = Color.Black)
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = HabitScheduleTheme.colors.barsColor
                )
            )

        }, bottomBar = {
            BottomBar(selected2 = true,
                onClickToScreen1 = {
                    navHostController.popBackStack()

                    navHostController.popBackStack()

                    navHostController.navigate(Screens.Main.route)
                })
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { isAddingHabitDialogOpen = true },

                containerColor = HabitScheduleTheme.colors.barsColor
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add habit")
            }
        },
            floatingActionButtonPosition = FabPosition.End
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(HabitScheduleTheme.colors.background)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                        .height(50.dp),

                    textStyle = TextStyle(color = Color.Black),

                    value = textSearch,

                    onValueChange = {
                        textSearch = it
                    },

                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                    },

                    placeholder = { Text(text = "Искать в заметках") },

                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black
                    )
                )

                if (isAddingHabitDialogOpen) {
                    AddingHabitDialog(
                        onDismissRequest = { isAddingHabitDialogOpen = false },

                        onAddNewItem = { item ->

                            cor.launch {

                                db.insert(item)

                                itemsList.collect { listik = it }

                                logExecutor(
                                    suffixTag = LogType.Database.value,
                                    message = "обновление при вставке"
                                )
                            }

                        }
                    )
                }

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(
                        listik
                            .let {
                                if (textSearch.isEmpty()) {
                                    it
                                } else {
                                    it.filter { hitem ->
                                        hitem.title.contains(textSearch) || hitem.text.contains(
                                            textSearch
                                        )
                                    }
                                }
                            }
                    ) { item ->
                        item.CardOfHabit()
                    }
                }
            }
        }
    }
}
