package com.example.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.jetpack.tool.TitleUtil
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(R.layout.main_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startToRoom()
        startToMvvm()
        startToRoomRxjava()
        startPading()
        startPagingReqyest()
        initTitle()
    }

    fun startToRoom() {
        roomStartBtn.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("room", "room_fragment")
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_roomFragment, bundle)
        }
    }

    fun startToMvvm() {
        var bundle = Bundle()
        bundle.putString("mvvm", "mvvm_fragment")
        mvvmStartBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_mvvmFragment, bundle))
    }

    fun startToRoomRxjava() {
        roomRxjavaStartBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_roomFragment2))
    }

    fun startPading() {
        padingBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_padingFragment))
    }

    fun startPagingReqyest(){
        padingRequestBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_pagingRequestFragment))
    }

    fun initTitle() {
        TitleUtil(context as MainActivity).setCenterTitle("选择操作")
                .setLeftImgVisible(false)
    }
}