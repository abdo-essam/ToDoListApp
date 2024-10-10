package com.ae.todolistapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ae.todolistapp.model.Task
import com.ae.todolistapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    // LiveData that directly observes the task list from the repository using Flow
    val taskList: LiveData<List<Task>> = taskRepository.loadTasks().asLiveData()

    fun search(queryWord: String): LiveData<List<Task>> {
        // Search tasks and return LiveData for observing search results
        return taskRepository.search(queryWord).asLiveData()
    }

    fun setChecked(task: Task) {
        // Launch a coroutine in the viewModelScope to handle background operations
        viewModelScope.launch {
            taskRepository.setChecked(task)
            // No need to call loadTasks() here since the task list is already being observed in real-time
        }
    }
}
