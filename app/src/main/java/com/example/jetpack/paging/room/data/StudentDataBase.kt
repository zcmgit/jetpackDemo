package com.example.jetpack.paging.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlin.concurrent.thread

@Database(entities = arrayOf(Student::class), version = 1)
abstract class StudentDataBase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        private var INSTANCE: StudentDataBase? = null
        fun getInstance(context: Context): StudentDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        StudentDataBase::class.java, "student")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                fillInDB(context)
                            }
                        })
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE as StudentDataBase
        }

        //初始化插入数据
        private fun fillInDB(context: Context) {
            thread(start = true) {
                getInstance(context).studentDao().insertAll(
                        studentNames.map { Student(0, it) }
                )
            }
        }

        private var studentNames = getStudentNames()

        fun getStudentNames(): List<String> {
            var studentNames = arrayListOf<String>()
            for (i in 0..100) {
                studentNames.add("张三" + i)
            }
            return studentNames
        }
    }


}

