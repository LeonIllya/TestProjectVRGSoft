package com.example.testprojectvrgsoft.di

import com.example.testprojectvrgsoft.adapters.NewsAdapter
import com.example.testprojectvrgsoft.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepositoryImpl {
        return NewsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}