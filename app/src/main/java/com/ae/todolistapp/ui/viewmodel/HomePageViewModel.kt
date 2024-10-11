package com.ae.todolistapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ae.todolistapp.model.Task
import com.ae.todolistapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    val taskList = MutableLiveData<List<Task>>()

    init {
        loadTasks()
    }

    fun loadTasks() {
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = taskRepository.loadTasks()
        }
    }

    fun search(queryWord: String) {
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = taskRepository.search(queryWord)
        }
    }

    fun setChecked(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.setChecked(task)
            loadTasks()
        }
    }
}
