package com.dts.retrofit.presentation.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dts.retrofit.presentation.tabs.NewsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val tabs = mutableListOf<NewsFragment>()

    @SuppressLint("NotifyDataSetChanged")
    fun addTab(feed: RssFeeds, rssFeeds: RssFeeds?) {
        tabs.add(NewsFragment(feed.source, feed.path))
        if (rssFeeds != null) {
            tabs.add(NewsFragment(rssFeeds.source, rssFeeds.path))
        }
        notifyDataSetChanged()
    }

    fun getTitle(position: Int): String {
        return tabs[position].getTitle()
    }

    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabs[position]
    }
}

data class RssFeeds(
    val source: String,
    val path: String
)
