package com.example.testprojectvrgsoft.repository

import com.example.testprojectvrgsoft.newsApi.RetrofitInstance.Companion.api
import com.example.testprojectvrgsoft.response.NewsResponse
import retrofit2.Response

class NewsRepositoryImpl: NewsRepository {
    override suspend fun getTopHeadlines(country: String): Response<NewsResponse> =
        api.getTopHeadlines(country)

    override suspend fun searchNews(query: String): Response<NewsResponse> =
        api.searchNews(query)

    override suspend fun getNewsByCategory(category: String, country: String): Response<NewsResponse> =
        api.getNewsByCategory(country, category)
}
