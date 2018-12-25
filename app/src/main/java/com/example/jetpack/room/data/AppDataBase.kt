package com.example.jetpack.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpack.room.entity.UserEntity

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        var INSTANCE: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDataBase::class.java, "user")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE as AppDataBase
        }

        fun getUserDao(context: Context): UserDao {
            return getInstance(context).userDao()
        }
    }
}