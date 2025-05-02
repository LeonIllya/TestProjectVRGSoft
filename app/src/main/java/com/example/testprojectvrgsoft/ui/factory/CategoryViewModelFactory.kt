package com.example.testprojectvrgsoft.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testprojectvrgsoft.repository.NewsRepositoryImpl
import com.example.testprojectvrgsoft.ui.viewmodel.CategoryViewModel

class CategoryViewModelFactory(private val repository: NewsRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
