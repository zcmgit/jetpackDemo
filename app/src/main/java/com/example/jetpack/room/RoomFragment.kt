package com.example.jetpack.room

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R
import com.example.jetpack.databinding.RoomFragmentBinding
import com.example.jetpack.room.adapter.UserListAdapter
import com.example.jetpack.room.data.AppDataBase
import com.example.jetpack.room.entity.UserEntity
import com.example.jetpack.tool.TitleUtil
import kotlinx.android.synthetic.main.room_fragment.*
import org.jetbrains.anko.toast

class RoomFragment : Fragment() {
    lateinit var userAdapter: UserListAdapter

    lateinit var viewModel: RoomViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var binder = DataBindingUtil.inflate<RoomFragmentBinding>(inflater, R.layout.room_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = viewModel.userAdapter.apply {
            setOnItemClickListener(object : UserListAdapter.OnItemClickListener {
                override fun OnItemClick(position: Int, userEntity: UserEntity) {
                    var bundle = Bundle()
                    bundle.putSerializable("user_info", userEntity)
                    Navigation.findNavController(view).navigate(R.id.action_roomFragment_to_addUserInfoFragment, bundle)
                }

                override fun onDeleteClick(userEntity: UserEntity) {
                    viewModel.deleteData(context!!, userEntity)
                }
            })
        }
        userList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
        viewModel.refreshUserList(this.context!!)
        //插入数据跳转
        insertBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_roomFragment_to_addUserInfoFragment)
        }
        //删除所有数据数据
        deleteAllBtn.setOnClickListener {
            viewModel.deleteAllData(context!!)
            if (userAdapter.userInfos.isEmpty()) {
                context!!.toast("清除成功")
            } else {
                context!!.toast("清除失败")
            }
        }
        //刷新数据
        refreshBtn.setOnClickListener {
            viewModel.refreshUserList(this.context!!)
        }
        initTitle()
    }
    fun initTitle(){
        TitleUtil(context as Activity).setCenterTitle("数据库操作")
                .setLeftImgClick(View.OnClickListener {
                    Navigation.findNavController(it).popBackStack()
                })
    }
}