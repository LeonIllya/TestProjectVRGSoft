package com.example.testprojectvrgsoft.repository

import com.example.testprojectvrgsoft.response.NewsResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getTopHeadlines(country: String): Response<NewsResponse>

    suspend fun searchNews(query: String): Response<NewsResponse>

    suspend fun getNewsByCategory(category: String, country: String): Response<NewsResponse>
}