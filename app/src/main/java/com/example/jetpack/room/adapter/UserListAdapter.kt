package com.example.jetpack.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.BR
import com.example.jetpack.R
import com.example.jetpack.databinding.UserListItemBinding
import com.example.jetpack.room.entity.UserEntity

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.Holder>() {

    var userInfos: MutableList<UserEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var viewDataBinding = DataBindingUtil.inflate<UserListItemBinding>(LayoutInflater.from(parent.context), R.layout.user_list_item, parent, false)
        return Holder(viewDataBinding)
    }

    override fun getItemCount(): Int {
        return userInfos.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.dataBinding?.setVariable(BR.user, userInfos[position])
        holder.dataBinding?.executePendingBindings()
        holder.itemView.setOnClickListener {
            onItemClickListener?.OnItemClick(position, userInfos.get(position))
        }

        var deleteImg = holder.itemView.findViewById<ImageView>(R.id.deleteImg)
        deleteImg.setOnClickListener {
            onItemClickListener?.onDeleteClick(userInfos.get(position))
        }
    }

    class Holder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        var dataBinding: ViewDataBinding? = viewDataBinding
    }

    fun setUserInfo(userInfos: MutableList<UserEntity>) {
        this.userInfos = userInfos
    }

    /**
     * 申明接口类型的变量
     */
    private var onItemClickListener: OnItemClickListener? = null

    /**
     * 自定义开放的方法给接口赋值初始化
     * @param onItemClickListener
     */
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    /**
     * 自定义接口
     * @param <T>
     */
    interface OnItemClickListener {
        fun OnItemClick(position: Int, userEntity: UserEntity)
        fun onDeleteClick(userEntity: UserEntity)
    }
}