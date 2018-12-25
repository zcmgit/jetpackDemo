package com.example.jetpack.paging.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import com.example.jetpack.paging.room.data.Student

class StudentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.student_list_item, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.studentName)
    private val idView = itemView.findViewById<TextView>(R.id.studentId)
    var student: Student? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(student: Student?) {
        this.student = student
        nameView.text = student?.name
        idView.text = student?.id.toString()
    }
}