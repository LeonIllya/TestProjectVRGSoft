package com.example.testprojectvrgsoft.models

data class Article(
    var id: Long? = null,
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?, val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)
