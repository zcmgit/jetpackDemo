package com.example.jetpack.paging.request.api.repository

/**
 * 自定义服务器错误
 */
class ServerException(val code: Int, val msg: String) : RuntimeException()
