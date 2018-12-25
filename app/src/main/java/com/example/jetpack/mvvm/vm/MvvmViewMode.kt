package com.example.jetpack.mvvm.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.jetpack.mvvm.bean.NewsLatestBean
import com.example.jetpack.mvvm.bean.Story
import com.example.jetpack.mvvm.mode.repository.Repository
import com.example.jetpack.mvvm.view.adapter.NewLatestAdapter

class MvvmViewMode : ViewModel() {
    var responses: LiveData<NewsLatestBean>? = null
    var isRefresh= MutableLiveData<Boolean>()
    var story = ArrayList<Story>()

    init {
        responses = Transformations.switchMap(isRefresh){
            Repository.getInfo()
        }
    }

    val adapter: NewLatestAdapter by lazy {
        NewLatestAdapter()
    }

    fun notifyDataSetChanged(newsLatestBean: NewsLatestBean){
        this.story = newsLatestBean.stories
        adapter.setNewsLatest(newsLatestBean.stories)
        adapter.notifyDataSetChanged()
    }
}
