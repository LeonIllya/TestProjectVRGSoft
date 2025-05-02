package com.example.testprojectvrgsoft.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojectvrgsoft.models.Article
import com.example.testprojectvrgsoft.repository.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: NewsRepositoryImpl): ViewModel() {
    private val _categoryNewsList = MutableLiveData<List<Article>>()
    val categoryNewsList: LiveData<List<Article>> = _categoryNewsList

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        viewModelScope.launch {
            val response = repository.getTopHeadlines("us")
            if (response.isSuccessful) {
                _categoryNewsList.postValue(response.body()?.articles)
            }
        }
    }

    fun getCategoryNews(category: String) {
        viewModelScope.launch {
            val response = repository.getNewsByCategory(category, "us")
            if (response.isSuccessful) {
                _categoryNewsList.postValue(response.body()?.articles)
            }
        }
    }
}
