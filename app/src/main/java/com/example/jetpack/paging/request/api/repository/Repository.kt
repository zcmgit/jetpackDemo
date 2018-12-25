package com.example.jetpack.paging.request.api.repository

import android.util.Log
import com.example.jetpack.paging.request.api.ApiServer
import com.example.jetpack.paging.request.api.RequestManager
import com.example.jetpack.paging.request.api.RetrofitManager
import com.example.jetpack.paging.request.data.bean.ArticleBean


object Repository {
    fun getInfo(pageNum: Int): ArticleBean {
        var temp: ArticleBean? = null
        RequestManager.execute(RetrofitManager.create(ApiServer::class.java).getArticleInfo(pageNum),
                object : BaseObserver<ArticleBean>(){
                    override fun onSuccess(t: ArticleBean) {
                        temp = t
                    }

                    override fun onFail(msg: String) {
                        Log.d("TAG",msg)
                    }

                })
        return temp!!
    }
}