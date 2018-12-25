package com.example.jetpack.paging.request.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.jetpack.paging.request.api.ApiServer
import com.example.jetpack.paging.request.api.RequestManager
import com.example.jetpack.paging.request.api.RetrofitManager
import com.example.jetpack.paging.request.api.repository.BaseObserver
import com.example.jetpack.paging.request.api.repository.Repository
import com.example.jetpack.paging.request.data.bean.ArticleBean
import com.example.jetpack.paging.request.data.bean.DatasItem
import com.example.jetpack.paging.room.data.Student

class LanguageDataSource : PageKeyedDataSource<Int, DatasItem>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DatasItem>) {
        RequestManager.execute(RetrofitManager.create(ApiServer::class.java).getArticleInfo(0),
                object : BaseObserver<ArticleBean>(){
                    override fun onSuccess(t: ArticleBean) {
                        callback.onResult(t.datas!!, null, 1)
                    }

                    override fun onFail(msg: String) {
                        Log.d("TAG",msg)
                    }

                })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DatasItem>) {
        RequestManager.execute(RetrofitManager.create(ApiServer::class.java).getArticleInfo(params.key),
                object : BaseObserver<ArticleBean>(){
                    override fun onSuccess(t: ArticleBean) {
                        callback.onResult(t.datas!!, params.key + 1)
                    }

                    override fun onFail(msg: String) {
                        Log.d("TAG",msg)
                    }

                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DatasItem>) {
        RequestManager.execute(RetrofitManager.create(ApiServer::class.java).getArticleInfo(params.key),
                object : BaseObserver<ArticleBean>(){
                    override fun onSuccess(t: ArticleBean) {
                        callback.onResult(t.datas!!, params.key - 1)
                    }

                    override fun onFail(msg: String) {
                        Log.d("TAG",msg)
                    }
                })
    }
}