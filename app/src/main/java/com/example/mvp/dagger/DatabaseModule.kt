package com.example.mvp.dagger

import androidx.room.Room
import com.example.mvp.App
import com.example.mvp.mvp.model.repo.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()
}