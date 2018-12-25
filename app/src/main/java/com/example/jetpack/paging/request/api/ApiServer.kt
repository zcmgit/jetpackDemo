package com.example.jetpack.paging.request.api

import com.example.jetpack.paging.request.api.repository.BaseResponse
import com.example.jetpack.paging.request.data.bean.ArticleBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiServer {

    @GET("article/list/{pageNum}/json")
    fun getArticleInfo(@Path("pageNum") pageNum: Int): Observable<BaseResponse<ArticleBean>>

}