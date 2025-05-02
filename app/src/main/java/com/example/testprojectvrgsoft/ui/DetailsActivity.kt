package com.example.testprojectvrgsoft.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testprojectvrgsoft.R
import com.squareup.picasso.Picasso

class DetailsActivity: AppCompatActivity(R.layout.activity_details) {

    private lateinit var textDetailTitle: TextView
    private lateinit var imgDetailNews: ImageView
    private lateinit var textDetailAuthor: TextView
    private lateinit var textDetailTime: TextView
    private lateinit var textDetailDetail: TextView
    private lateinit var textDetailContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textDetailTitle = findViewById(R.id.text_detail_title)
        imgDetailNews = findViewById(R.id.img_detail_news)
        textDetailAuthor = findViewById(R.id.text_detail_author)
        textDetailTime = findViewById(R.id.text_detail_time)
        textDetailContent = findViewById(R.id.text_detail_content)
        textDetailDetail = findViewById(R.id.text_detail_detail)

        val title = intent.getStringExtra("title") ?: "Без назви"
        val author = intent.getStringExtra("author") ?: "Невідомий автор"
        val publishedAt = intent.getStringExtra("publishedAt") ?: "Невідома дата"
        val description = intent.getStringExtra("description") ?: "Опис відсутній"
        val content = intent.getStringExtra("content") ?: "Контент відсутній"
        val urlToImage = intent.getStringExtra("urlToImage")


        textDetailTitle.text = title
        textDetailAuthor.text = author
        textDetailTime.text = publishedAt
        textDetailDetail.text = description
        textDetailContent.text = content

        if (urlToImage != null) {
            Picasso.get().load(urlToImage).into(imgDetailNews)
        } else {
            imgDetailNews.setImageResource(R.drawable.placeholder_image)
        }
    }
}