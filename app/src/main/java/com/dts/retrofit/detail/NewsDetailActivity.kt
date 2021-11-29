package com.dts.retrofit.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dts.retrofit.R
import com.dts.retrofit.data.Item
import com.dts.retrofit.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsItem: Item? = intent?.extras?.getParcelable("news_item")
        if (newsItem != null)
            binding.tvTitle.text = newsItem.title.orEmpty()
        if (newsItem != null) {
            binding.tvDescription.text = newsItem.description.orEmpty()
        }
        binding.btnReadMore.setOnClickListener {
            if (newsItem != null) {
                binding.wbMoreContent.loadUrl(newsItem.guid.orEmpty())
            }
        }
    }
}
