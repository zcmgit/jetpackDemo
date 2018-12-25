package com.example.jetpack.mvvm.bean

data class ContentBean(val image: String = "",
                       val images: List<String>?,
                       val css: List<String> = arrayListOf(),
                       val shareUrl: String = "",
                       val gaPrefix: String = "",
                       val id: Int = 0,
                       val js: List<String> = arrayListOf(),
                       val body: String = "",
                       val title: String = "",
                       val type: Int = 0,
                       val imageSource: String = "")