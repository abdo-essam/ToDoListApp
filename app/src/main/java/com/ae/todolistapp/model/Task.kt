package com.ae.todolistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id") val taskId: Int = 0,
    @ColumnInfo(name = "task_title") val taskTitle: String,
    @ColumnInfo (name = "task_date") val taskDate: String,
    @ColumnInfo (name = "task_time") val taskTime: String,
    @ColumnInfo (name = "task_activated") var taskActivated: Int
): Serializable
