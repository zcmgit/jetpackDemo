package com.example.jetpack.paging.room.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.jetpack.paging.room.data.Student

class StudentAdapter : PagedListAdapter<Student, StudentViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        /**
         * 这个diff回调通知PagedListAdapter在新列表到来的时候如何计算列表差异
         *
         * 当您使用“add”按钮添加一个Student的时候，PagedListAdapter使用diffCallback t去检测到与以前的Item的不同，所以它只需要重画和重新绑定一个视图。
         *
         * @see android.support.v7.util.DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem == newItem
        }
    }
}
