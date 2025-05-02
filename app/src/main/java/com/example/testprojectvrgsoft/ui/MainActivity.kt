package com.example.testprojectvrgsoft.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectvrgsoft.R
import com.example.testprojectvrgsoft.adapters.NewsAdapter
import com.example.testprojectvrgsoft.repository.NewsRepositoryImpl
import com.example.testprojectvrgsoft.ui.factory.MainViewModelFactory
import com.example.testprojectvrgsoft.ui.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsAdapter = NewsAdapter()

        setupRecyclerView()
        observeNews()
        setupSearch()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_main)
        recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        newsAdapter.setOnItemClickListener { article ->
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("title", article.title)
                putExtra("author", article.author)
                putExtra("publishedAt", article.publishedAt)
                putExtra("description", article.description)
                putExtra("content", article.content)
                putExtra("urlToImage", article.urlToImage)
            }
            startActivity(intent)
        }
    }

    private fun observeNews() {
        viewModel.newsList.observe(this) { articles ->
            newsAdapter.differ.submitList(articles)
        }
    }

    private fun setupSearch() {
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchNews(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_categories -> {
                    startActivity(Intent(this, CategoryActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
