package com.ae.todolistapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ae.todolistapp.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}