package com.example.jetpack.mvvm.mode.repository

import androidx.lifecycle.MutableLiveData
import com.example.jetpack.mvvm.bean.ContentBean
import com.example.jetpack.mvvm.bean.NewsLatestBean
import com.example.jetpack.mvvm.mode.api.ApiServer
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager

object Repository {
    fun getInfo(): MutableLiveData<NewsLatestBean> {
        var temp = MutableLiveData<NewsLatestBean>()
        RequestManager.execute(RetrofitManager.create(ApiServer::class.java).getNewsLatest(),
                object : BaseObserver<NewsLatestBean>() {
                    override fun onSuccess(t: NewsLatestBean) {
                        temp.value = t
                    }

                    override fun onFail(msg: String) {
                        temp.value = null
                    }
                })
        return temp
    }


    fun getNewsLatestContent(id: Int): MutableLiveData<ContentBean> {
        var temp = MutableLiveData<ContentBean>()
        RequestManager.execute(RetrofitManager.create(ApiServer::class.java).getNewsLatestContent(id),
                object : BaseObserver<ContentBean>() {
                    override fun onSuccess(t: ContentBean) {
                        temp.value = t
                    }

                    override fun onFail(msg: String) {
                        temp.value = null
                    }
                })
        return temp
    }
}