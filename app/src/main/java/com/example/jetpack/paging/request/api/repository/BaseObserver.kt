package com.example.jetpack.paging.request.api.repository

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class BaseObserver<T> : Observer<T> {



    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        val  errorMsg = e.message.toString()
        onFail(errorMsg)
    }

    override fun onComplete() {}

    abstract fun onSuccess(t: T)

    abstract fun onFail(msg: String)
}
