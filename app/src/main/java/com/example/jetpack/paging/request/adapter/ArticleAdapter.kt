package com.example.jetpack.paging.request.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.jetpack.paging.request.data.bean.DatasItem

class ArticleAdapter : PagedListAdapter<DatasItem, ArticleViewHolder>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<DatasItem>(){
            override fun areItemsTheSame(oldItem: DatasItem, newItem: DatasItem): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DatasItem, newItem: DatasItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}