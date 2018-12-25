package com.example.jetpack.paging.room.data

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface StudentDao{

    @Query("SELECT * FROM student")
    fun searchAllStudent(): DataSource.Factory<Int,Student>

    @Delete
    fun delete(student: Student)

    @Update
    fun update(student: Student)

    @Insert
    fun insert(student: Student)

    @Insert
    fun insertAll(students: List<Student>)
}