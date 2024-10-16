package com.ae.todolistapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ae.todolistapp.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY task_activated DESC, task_date, task_time")
    suspend fun loadTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Update
    suspend fun setChecked(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task WHERE task_title like '%' || :queryWord || '%'")
    suspend fun search(queryWord: String): List<Task>
}
