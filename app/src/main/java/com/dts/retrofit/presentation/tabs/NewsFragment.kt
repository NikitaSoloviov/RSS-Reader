package com.dts.retrofit.presentation.tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.dts.retrofit.R
import com.dts.retrofit.adapter.NewsAdapter
import com.dts.retrofit.data.Item
import com.dts.retrofit.databinding.FragmmentFirstTabBinding
import com.dts.retrofit.detail.NewsDetailActivity
import com.dts.retrofit.domain.ApiClient
import com.dts.retrofit.domain.OnLoadNewsCallback
import com.dts.retrofit.presentation.model.NewsModel
import com.dts.retrofit.presentation.repository.NewsRepository
import timber.log.Timber
import java.util.*

class NewsFragment(private val sourceUrl: String, private val pathUrl: String) :
    Fragment(R.layout.fragmment_first_tab), OnLoadNewsCallback {

    private lateinit var binding: FragmmentFirstTabBinding
    private val adapter = NewsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        NewsRepository(requireContext()).getNews(pathUrl, sourceUrl, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmmentFirstTabBinding.bind(
            super.onCreateView(
                inflater,
                container,
                savedInstanceState
            )!!
        )
        return binding.root
    }

    override fun onLoad(items: List<Item>) {
        Timber.d("loaded data : $items")

        adapter.addNews(items.map { item ->
            NewsModel(
                item.title.orEmpty(),
                item.pubDate?.let { Item.formatter.parse(it) } ?: Date(),
                //it.pubDate,
                item.author.orEmpty(),
                item
            )
        })
    }


    override fun onFail(message: String) {
        Timber.d("Fail $message")
    }

    private fun setupAdapter() {
        Timber.d("Loaded data ")
        adapter.setOnClickListener {
            Timber.d("${it.title}")
            val intent = Intent(requireContext(), NewsDetailActivity::class.java)

            val bundle = Bundle()
            bundle.putParcelable("news_item", it.data)

            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.rvNews.adapter = adapter
    }

    fun getTitle() = "Tab: ${sourceUrl.toUri().host}"
}
