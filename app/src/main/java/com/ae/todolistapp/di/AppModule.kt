package com.ae.todolistapp.di

import android.content.Context
import androidx.room.Room
import com.ae.todolistapp.database.TaskDao
import com.ae.todolistapp.database.TaskDatabase
import com.ae.todolistapp.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepository(taskDao)
    }


    @Provides
    @Singleton
    fun provideTaskDao(@ApplicationContext context: Context): TaskDao {
        val db = Room.databaseBuilder(context, TaskDatabase::class.java, "database.sqlite")
            .createFromAsset("database.sqlite").build()
        return db.getTaskDao()
    }

}