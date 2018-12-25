package com.example.jetpack.paging.request.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.jetpack.paging.request.data.bean.DatasItem

class ItemDataSourceFactory() :
        DataSource.Factory<Int, DatasItem>() {
    val sourceLiveData = MutableLiveData<LanguageDataSource>()

    override fun create(): DataSource<Int, DatasItem> {
        val source = LanguageDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}