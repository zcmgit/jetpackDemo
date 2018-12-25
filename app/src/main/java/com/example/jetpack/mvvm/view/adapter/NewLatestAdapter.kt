package com.example.jetpack.mvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpack.BR
import com.example.jetpack.R
import com.example.jetpack.databinding.NewLatestListItemBinding
import com.example.jetpack.mvvm.bean.Story


class NewLatestAdapter : RecyclerView.Adapter<NewLatestAdapter.Holder>() {
    private var newsLatest = ArrayList<Story>()
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewLatestAdapter.Holder {
        context = parent.context
        var viewDataBinding = DataBindingUtil.inflate<NewLatestListItemBinding>(LayoutInflater.from(parent.context), R.layout.new_latest_list_item, parent, false)
        return Holder(viewDataBinding)
    }

    override fun getItemCount() = newsLatest.size

    override fun onBindViewHolder(holder: NewLatestAdapter.Holder, position: Int) {
        holder.dataBinding?.setVariable(BR.story, newsLatest[position])
        holder.dataBinding?.executePendingBindings()
        var numText: TextView = holder.itemView.findViewById(R.id.newsNum)
        numText.text = (position + 1).toString()

        var img: ImageView = holder.itemView.findViewById(R.id.newsImg)
        Glide.with(context).load(newsLatest[position].images[0]).into(img)

        holder.itemView.setOnClickListener{
            onItemClickListener!!.OnItemClick(position,newsLatest.get(position))
        }
    }

    class Holder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        var dataBinding: ViewDataBinding? = viewDataBinding
    }

    fun setNewsLatest(story: ArrayList<Story>) {
        newsLatest = story
    }

    /**
     * 申明接口类型的变量
     */
    private var onItemClickListener: OnItemClickListener? = null

    /**
     * 自定义开放的方法给接口赋值初始化
     * @param onItemClickListener
     */
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    /**
     * 自定义接口
     * @param <T>
     */
    interface OnItemClickListener{
        fun OnItemClick(position: Int,story: Story)
    }
}