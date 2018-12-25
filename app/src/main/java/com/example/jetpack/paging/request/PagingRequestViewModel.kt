package com.example.jetpack.paging.request

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpack.paging.request.data.ItemDataSourceFactory
import com.example.jetpack.paging.room.StudentViewModel

class PagingRequestViewModel : ViewModel(){
    companion object {
        private const val PAGE_SIZE: Int = 10
        private const val ENABLE_PLACEHOLDERS: Boolean = true
        private const val INITIAL_LOAD_SIZE_HINT = 10
    }
    val studentBuild = LivePagedListBuilder(ItemDataSourceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(PAGE_SIZE)
                    .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
                    .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                    .build()).build()
}