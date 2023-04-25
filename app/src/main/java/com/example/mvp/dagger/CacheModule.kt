package com.example.mvp.dagger

import androidx.room.Room
import com.example.mvp.App
import com.example.mvp.mvp.model.cache.IUserCache
import com.example.mvp.mvp.model.cache.room.RoomUserCache
import com.example.mvp.mvp.model.repo.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IUserCache = RoomUserCache(database)
}