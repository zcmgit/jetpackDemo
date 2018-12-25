package com.example.jetpack.paging.request.data.bean

data class ArticleBean(var over: Boolean = false,
                       var pageCount: Int = 0,
                       var total: Int = 0,
                       var curPage: Int = 0,
                       var offset: Int = 0,
                       var size: Int = 0,
                       var datas: List<DatasItem>?)