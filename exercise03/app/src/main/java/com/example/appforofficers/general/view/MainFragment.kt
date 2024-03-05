package com.example.appforofficers.general.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.appforofficers.R
import com.example.appforofficers.general.presenter.MainPresenter
import com.example.appforofficers.general.view.adapters.PagerAdapter
import com.example.appforofficers.incidents.view.IncidentsFragment
import com.example.appforofficers.zones.view.ZonesFragment
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment(), IMainView {
    private lateinit var tabLayout: TabLayout
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager2
    private val fragmentsList = listOf(
        ZonesFragment.newInstance(),
        IncidentsFragment.newInstance()
    )

    val iMainPresenter = MainPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.view_pager)
        pagerAdapter = PagerAdapter(requireActivity(), fragmentsList)
        viewPager.adapter = pagerAdapter
        tabLayout = view.findViewById(R.id.tab_layout)
    }

    override fun onResume() {
        super.onResume()
        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    iMainPresenter.onPageSelected(position)
                }
            }
        )
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    iMainPresenter.onTabSelected(tab.position)
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            }
        )
    }

    override fun setTab(position: Int) {
        tabLayout.setScrollPosition(position, 0f, true)
    }

    override fun setPage(position: Int) {
        viewPager.setCurrentItem(position, true)
    }

}