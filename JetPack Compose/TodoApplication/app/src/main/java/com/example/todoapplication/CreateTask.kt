package com.example.todoapplication

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDropdown(options: List<Boolean>, selectedOptionText: MutableState<Boolean>) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        TextField(
            readOnly = true,
            value = selectedOptionText.value.toString(),
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(16.dp)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.padding(16.dp)
        ) {
            options.forEach { selectionOption: Boolean ->
                DropdownMenuItem(
                    text = { Text(selectionOption.toString()) },
                    onClick = {
                        selectedOptionText.value = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}


//@Composable
//fun create_task(
//    manageTaskText:String,
//    navController: NavController,
//    viewModel: ViewModelMain,
//) {
//    val text = remember { mutableStateOf("") }
//    val options = listOf(true, false)
//    val selectedOptionText = remember { mutableStateOf(options[0]) }
//
//    val context = LocalContext.current
//
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        Text(
//            manageTaskText,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 20.dp),
//            textAlign = TextAlign.Center,
//            fontSize = 30.sp
//        )
//        Text("Enter the Task", modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
//        TextField(
//            value = text.value,
//            onValueChange = { text.value = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        )
//        Text("Is Task Already completed?", modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
//        SimpleDropdown(options, selectedOptionText)
//        Button(
//            onClick = {
//                Toast.makeText(context, "Task Created Successfully", Toast.LENGTH_LONG).show()
//                viewModel.addTask(
//                    title = text.value,
//                    completed = selectedOptionText.value
//                )
//                navController.navigate("home")
//
//            }, colors = ButtonDefaults.buttonColors(
//                containerColor = Color.Green,
//                contentColor = Color.Black
//            ), modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text("Create new Task")
//
//        }
//
//    }
//}



@Composable
fun create_task(
    manageTaskText:String,
    selectedOption:Boolean,
    navController: NavController,
    viewModel: ViewModelMain,
) {
    val text = remember { mutableStateOf("") }
    val options = listOf(true, false)
    val selectedOptionText = remember { mutableStateOf(selectedOption) }

    val context = LocalContext.current


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            manageTaskText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
        Text("Enter the Task", modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text("Is Task Already completed?", modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
        SimpleDropdown(options, selectedOptionText)
        Button(
            onClick = {
                Toast.makeText(context, "Task Created Successfully", Toast.LENGTH_LONG).show()
                viewModel.addTask(
                    title = text.value,
                    completed = selectedOptionText.value
                )
                navController.navigate("home")

            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.Black
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Create new Task")

        }

    }
}