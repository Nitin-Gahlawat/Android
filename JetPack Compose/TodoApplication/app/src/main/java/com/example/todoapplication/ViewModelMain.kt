package com.example.todoapplication

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.di.RoomDatabase.Tasks
import com.example.todoapplication.di.RoomDatabase.TasksDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelMain @Inject constructor(
    private val tasks_dao: TasksDao
) :
    ViewModel() {



    var _tasks = mutableStateListOf<Tasks>()
        private set

    val test = MutableStateFlow(arrayOf(""))

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _tasks.addAll(tasks_dao.getAllTasks())
        }
    }

    fun addTask(title: String, completed: Boolean) {
        val newTask = Tasks( body = title, isCompleted = completed)
        _tasks.add(newTask)

        viewModelScope.launch(Dispatchers.IO) {
            tasks_dao.insert(newTask)
        }
    }

    fun toggleDone(id: Int) {
        val index = _tasks.indexOfFirst { it.id == id }
        if (index != -1) {
            val task = _tasks[index]
            val toggledState = !task.isCompleted
            viewModelScope.launch(Dispatchers.IO) {
                tasks_dao.update(Tasks(task.id, task.body, toggledState))
            }
            _tasks[index] = task.copy(isCompleted = toggledState)
        }
    }

    fun removeTask(task: Tasks) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                tasks_dao.delete(task)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            _tasks.remove(task)
        }
    }
}