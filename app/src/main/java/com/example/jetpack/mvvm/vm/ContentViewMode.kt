package com.example.jetpack.mvvm.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.jetpack.mvvm.bean.ContentBean
import com.example.jetpack.mvvm.mode.repository.Repository

class ContentViewMode : ViewModel() {
    var responses: LiveData<ContentBean>? = null
    var newsId = MutableLiveData<Int>()

    init {
        responses = Transformations.switchMap(newsId){
            Repository.getNewsLatestContent(newsId.value!!)
        }
    }

}