package com.example.jetpack.mvvm.bean

import java.io.Serializable

data class NewsLatestBean(
    val date: String,
    val stories: ArrayList<Story>,
    val top_stories: ArrayList<TopStory>
)

data class Story(
    val ga_prefix: String,
    val id: Int,
    val images: ArrayList<String>,
    val title: String,
    val type: Int
) : Serializable

data class TopStory(
    val ga_prefix: String,
    val id: Int,
    val image: String,
    val title: String,
    val type: Int
)