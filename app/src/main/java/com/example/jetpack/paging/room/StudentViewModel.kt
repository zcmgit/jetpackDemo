package com.example.jetpack.paging.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpack.paging.room.data.Student
import com.example.jetpack.paging.room.data.StudentDataBase

class StudentViewModel(context: Context) : ViewModel() {
    val dao = StudentDataBase.getInstance(context).studentDao()

    companion object {
        private const val PAGE_SIZE = 10
        private const val ENABLE_PLACEHOLDERS = true
    }

    val studentBuild = LivePagedListBuilder(dao.searchAllStudent(),
            PagedList.Config.Builder()
                    .setPageSize(PAGE_SIZE)
                    .setInitialLoadSizeHint(10)
                    .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                    .build()).build()

    fun insert(name: String) {
        dao.insert(Student(0,name))
    }

    fun delete(student: Student) {
        dao.delete(student)
    }
}