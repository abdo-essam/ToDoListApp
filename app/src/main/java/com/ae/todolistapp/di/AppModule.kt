package com.ae.todolistapp.di

import android.app.Application
import com.ae.todolistapp.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTaskRepository(app: Application): TaskRepository {
        return TaskRepository(app)
    }

}