package com.example.todoapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapplication.di.RoomDatabase.Tasks
import com.example.todoapplication.ui.theme.TodoApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Full_navigation()
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Full_navigation() {
    val navController = rememberNavController()
    TodoApplicationTheme {
        val viewModel: ViewModelMain = hiltViewModel()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navController.navigate("CreateTask") },
                            containerColor = MaterialTheme.colorScheme.primary
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) {
                    MakeApp(navController, viewModel)
                }
            }
            composable("CreateTask") {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    create_task(
                        manageTaskText = "Create a New Task",
                        selectedOption = false,
                        navController,
                        viewModel
                    )
                }
            }
            composable("UpdateTask") {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    create_task(
                        manageTaskText = "Update Task",
                        selectedOption = true,
                        navController,
                        viewModel
                    )
                }
            }
        }

    }
}

@Composable
fun MakeApp(
    navController: NavController,
    viewModel: ViewModelMain
) {
    Text(
        "Tasks",
        modifier = Modifier.padding(top = 40.dp, start = 10.dp, bottom = 20.dp),
        fontSize = 35.sp,
        fontFamily = FontFamily.Serif
    )

    val list = viewModel._tasks

    if (list.size == 0) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Enter some Task to Display",
                fontSize = 17.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )
        }
    } else {

        LazyColumn(
            modifier = Modifier.padding(
                top = 80.dp, start = 10.dp, end = 10.dp, bottom = 40.dp
            )
        ) {

            items(list) { data ->
                EachItem(
                    data,
                    viewModel = viewModel,
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .clickable {
                            navController.navigate("UpdateTask")
                        }
                )
            }
        }
    }
}


@Composable
fun EachItem(task: Tasks, viewModel: ViewModelMain, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted, onCheckedChange = {
                    viewModel.toggleDone(task.id)
                }, colors = CheckboxDefaults.colors(Color.Green)
            )
            Text(
                task.body,
                textAlign = TextAlign.Start,
                fontSize = 25.sp,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { viewModel.removeTask(task) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
