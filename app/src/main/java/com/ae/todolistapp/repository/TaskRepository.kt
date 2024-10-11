package com.ae.todolistapp.repository

import android.app.Application
import com.ae.todolistapp.database.TaskDao
import com.ae.todolistapp.database.TaskDatabase
import com.ae.todolistapp.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(app: Application) {

    private var taskDao : TaskDao = TaskDatabase.getDatabase(app).getTaskDao()

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun loadTasks(): List<Task> =
        withContext(Dispatchers.IO) {
            return@withContext taskDao.loadTasks()
        }

    suspend fun search(queryWord: String): List<Task> =
        withContext(Dispatchers.IO) {
            return@withContext taskDao.search(queryWord)
        }

    suspend fun createTask(taskTitle: String, taskDate: String, taskTime: String) {
        val newTask = Task(0, taskTitle, taskDate, taskTime, 0)
        taskDao.createTask(newTask)
    }

    suspend fun updateTask(taskId: Int, taskTitle: String, taskDate: String, taskTime: String) {
        val updatedTask = Task(taskId, taskTitle, taskDate, taskTime, 0)
        taskDao.updateTask(updatedTask)
    }

    suspend fun setChecked(task: Task) {
        if (task.taskActivated == 0) {
            task.taskActivated = 1
        } else if (task.taskActivated == 1) {
            task.taskActivated = 0
        }
        taskDao.setChecked(task)
    }
}
