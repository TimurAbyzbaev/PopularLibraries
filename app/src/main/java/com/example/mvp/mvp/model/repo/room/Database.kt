package com.example.mvp.mvp.model.repo.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvp.mvp.model.entity.room.RoomGithubRepository
import com.example.mvp.mvp.model.entity.room.RoomGithubUser
import com.example.mvp.mvp.model.entity.room.dao.RepositoryDao
import com.example.mvp.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities =
    [RoomGithubUser::class,
        RoomGithubRepository::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance
            ?: throw java.lang.RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}