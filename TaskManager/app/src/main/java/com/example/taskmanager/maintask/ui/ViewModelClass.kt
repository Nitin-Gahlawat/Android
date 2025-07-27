package com.example.taskmanager.maintask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.maintask.di.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelClass @Inject constructor(var db: TaskDao) : ViewModel() {

    var dataAdapter = TaskDataAdapter()

    init {
        setAdapter()
    }

    fun deleteSelected() {
        var x = dataAdapter.currentList
        for (i in x) {
            if (i.selected == true) {
                viewModelScope.launch(Dispatchers.IO) {
                    db.Delete(i)
                }
            }
        }
        dataAdapter.submitList(x)
    }

    fun setAdapter() {

        dataAdapter.deleteItem = {
            viewModelScope.launch(Dispatchers.IO) {
                db.Delete(it)
            }
        }

        viewModelScope.launch(Dispatchers.Main) {
            db.getAll().collectLatest {
                dataAdapter.submitList(it)
            }
        }

    }


}