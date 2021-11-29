package com.dts.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dts.retrofit.databinding.ActivityMainBinding
import com.dts.retrofit.databinding.BottomSheetBinding
import com.dts.retrofit.presentation.adapter.RssFeeds
import com.dts.retrofit.presentation.adapter.ViewPagerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingBottom: BottomSheetBinding
    private val pagerAdapter by lazy { ViewPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingBottom = BottomSheetBinding.inflate(layoutInflater)

        binding.btnShowBottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            bindingBottom.btnDismiss.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(true)

            dialog.setContentView(bindingBottom.root)

            dialog.show()
        }

        pagerAdapter.addTab(
            RssFeeds(
                "https://www.pravda.com.ua/",
                "rss/view_pubs/"
            ),
            null
        )

        binding.pager.adapter = pagerAdapter
        bindingBottom.btnAdd.setOnClickListener {

            pagerAdapter.addTab(
                RssFeeds(
                    bindingBottom.etSource.text.toString(),
                    bindingBottom.etPath.text.toString()
                ),
                null
            )
        }

        Timber.plant(Timber.DebugTree())
        Timber.d("App IS Started")

        TabLayoutMediator(
            binding.tabLayout,
            binding.pager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = pagerAdapter.getTitle(position)
                }

            }).attach()
    }
}
