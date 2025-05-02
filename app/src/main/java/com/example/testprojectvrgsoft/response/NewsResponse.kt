package com.example.testprojectvrgsoft.response

import com.example.testprojectvrgsoft.models.Article

data class NewsResponse(
        val status: String,
        val totalResult: Int,
        val articles: MutableList<Article>
)
