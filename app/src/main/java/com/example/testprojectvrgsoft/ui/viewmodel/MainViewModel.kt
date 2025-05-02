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
class MainViewModel @Inject constructor(private val repository: NewsRepositoryImpl): ViewModel() {
    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>> = _newsList

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        viewModelScope.launch {
            val response = repository.getTopHeadlines("us")
            if (response.isSuccessful) {
                _newsList.postValue(response.body()?.articles)
            }
        }
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            val response = repository.searchNews(query)
            if (response.isSuccessful) {
                _newsList.postValue(response.body()?.articles)
            }
        }
    }
}
