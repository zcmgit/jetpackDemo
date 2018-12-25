package com.example.jetpack.mvvm.mode.api

import com.example.jetpack.mvvm.bean.ContentBean
import com.example.jetpack.mvvm.bean.NewsLatestBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServer {

    @GET("4/news/latest")
    fun getNewsLatest(): Observable<NewsLatestBean>

    @GET("4/news/{id}")
    fun getNewsLatestContent(@Path("id") id: Int): Observable<ContentBean>
}