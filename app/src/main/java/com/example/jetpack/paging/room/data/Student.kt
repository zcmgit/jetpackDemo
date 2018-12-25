package com.example.jetpack.paging.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String
)