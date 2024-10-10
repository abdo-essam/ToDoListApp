package com.ae.todolistapp.repository

import com.ae.todolistapp.database.TaskDao
import com.ae.todolistapp.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.deleteTask(task)
        }
    }

    // Flow for real-time updates
    fun loadTasks(): Flow<List<Task>> = taskDao.loadTasks()

    // Flow for real-time search results
    fun search(queryWord: String): Flow<List<Task>> = taskDao.search(queryWord)

    suspend fun createTask(taskTitle: String, taskDate: String, taskTime: String) {
        withContext(Dispatchers.IO) {
            val newTask = Task(0, taskTitle, taskDate, taskTime, 0)
            taskDao.createTask(newTask)
        }
    }

    suspend fun updateTask(taskId: Int, taskTitle: String, taskDate: String, taskTime: String) {
        withContext(Dispatchers.IO) {
            val updatedTask = Task(taskId, taskTitle, taskDate, taskTime, 0)
            taskDao.updateTask(updatedTask)
        }
    }

    suspend fun setChecked(task: Task) {
        val updatedTask = task.copy(
            taskActivated = if (task.taskActivated == 0) 1 else 0
        )
        withContext(Dispatchers.IO) {
            taskDao.setChecked(updatedTask)
        }
    }
}
