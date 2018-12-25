package com.example.jetpack.paging.request.api

import com.example.jetpack.paging.request.api.repository.BaseObserver
import com.example.jetpack.paging.request.api.repository.BaseResponse
import com.example.jetpack.paging.request.api.repository.ServerResultFunc
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object RequestManager {

    /**
     * 通用网络请求方法
     */
    fun <T> execute(observable: Observable<BaseResponse<T>>, observer: BaseObserver<T>){
        observable
                .map(ServerResultFunc())
                .subscribeOn(Schedulers.io()) //2.请求在IO线程
                .observeOn(AndroidSchedulers.mainThread()) //3观察处理数据在主线程
               .subscribe(observer)
    }
}
