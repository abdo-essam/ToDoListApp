package com.ae.todolistapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ae.todolistapp.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    // Using Flow for real-time updates in loadTasks
    @Query("SELECT * FROM task ORDER BY task_activated DESC, task_date, task_time")
    fun loadTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // Optional conflict strategy
    suspend fun createTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Update
    suspend fun setChecked(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    // Using Flow for real-time search results
    @Query("SELECT * FROM task WHERE task_title LIKE '%' || :queryWord || '%'")
    fun search(queryWord: String): Flow<List<Task>>
}
