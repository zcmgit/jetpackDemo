package com.kotlin.test.base.network.request

import com.example.jetpack.mvvm.mode.repository.BaseObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object RequestManager {

    /**
     * 通用网络请求方法
     */
    fun <T> execute(observable: Observable<T>,observer: BaseObserver<T>){
        observable
                .subscribeOn(Schedulers.io()) //2.请求在IO线程
                .observeOn(AndroidSchedulers.mainThread()) //3观察处理数据在主线程
               .subscribe(observer)
    }
}
