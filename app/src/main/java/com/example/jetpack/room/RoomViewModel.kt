package com.example.jetpack.room

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.jetpack.room.adapter.UserListAdapter
import com.example.jetpack.room.data.AppDataBase
import com.example.jetpack.room.entity.UserEntity

class RoomViewModel : ViewModel(){

    val userAdapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    fun refreshUserList(context: Context){
        var useerInfos = AppDataBase.getUserDao(context).searchAllInfo()
        userAdapter.setUserInfo(useerInfos.toMutableList())
        userAdapter.notifyDataSetChanged()
    }

    fun deleteAllData(context: Context){
        AppDataBase.getUserDao(context).deleteAll()
        userAdapter.userInfos = arrayListOf()
        userAdapter.notifyDataSetChanged()
    }

    fun deleteData(context: Context,userEntity: UserEntity){
        AppDataBase.getUserDao(context).delete(userEntity)
        userAdapter.userInfos.remove(userEntity)
        userAdapter.notifyDataSetChanged()
    }
}