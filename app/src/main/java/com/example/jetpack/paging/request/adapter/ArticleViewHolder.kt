package com.example.jetpack.paging.request.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import com.example.jetpack.paging.request.data.bean.DatasItem
import org.w3c.dom.Text

class ArticleViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.article_list_item,parent,false)
){

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val time = itemView.findViewById<TextView>(R.id.time)
    private val typeTxt = itemView.findViewById<TextView>(R.id.typeTxt)
    private val author = itemView.findViewById<TextView>(R.id.author)
    var datasItem: DatasItem? = null

    fun bindTo(datasItem: DatasItem?){
        this.datasItem = datasItem
        title.text = datasItem?.title
        time.text = datasItem?.niceDate
        typeTxt.text = datasItem?.chapterName
        author.text = datasItem?.author
    }
}
