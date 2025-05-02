package com.example.testprojectvrgsoft.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectvrgsoft.R
import com.example.testprojectvrgsoft.adapters.NewsAdapter
import com.example.testprojectvrgsoft.ui.viewmodel.CategoryViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryActivity: AppCompatActivity(R.layout.activity_categories) {
    private val viewModel: CategoryViewModel by viewModels()

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = NewsAdapter()

        setupRecyclerView()
        setupCategoryButtons()
        observeCategoryNews()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_category_news)
        recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@CategoryActivity)
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

    private fun setupCategoryButtons() {
        val firstButton = findViewById<Button>(R.id.button_one)
        val secondButton = findViewById<Button>(R.id.button_two)
        val thirdButton = findViewById<Button>(R.id.button_three)
        val fourthButton = findViewById<Button>(R.id.button_four)
        val fifthButton = findViewById<Button>(R.id.button_five)
        val sixthButton = findViewById<Button>(R.id.button_six)
        val seventhButton = findViewById<Button>(R.id.button_seven)

        firstButton.setOnClickListener { fetchNewsByCategory("business") }
        secondButton.setOnClickListener { fetchNewsByCategory("entertainment") }
        thirdButton.setOnClickListener { fetchNewsByCategory("general") }
        fourthButton.setOnClickListener { fetchNewsByCategory("health") }
        fifthButton.setOnClickListener { fetchNewsByCategory("science") }
        sixthButton.setOnClickListener { fetchNewsByCategory("sports") }
        seventhButton.setOnClickListener { fetchNewsByCategory("technology") }
    }

    private fun fetchNewsByCategory(category: String) {
        viewModel.getCategoryNews(category)
    }

    private fun observeCategoryNews() {
        viewModel.categoryNewsList.observe(this) { articles ->
            newsAdapter.differ.submitList(articles)
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.navigation_categories -> true
                else -> false
            }
        }
    }
}
