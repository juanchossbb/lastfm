package com.jhurtado.lastfm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jhurtado.lastfm.R
import com.jhurtado.lastfm.ui.artistlist.ArtistListFragment
import com.jhurtado.lastfm.ui.tracklist.TrackListFragment

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
class ListTabActivity : AppCompatActivity() {
    lateinit var viewpager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_activity)
        viewpager = findViewById(R.id.vp_tab_layout)
        setupViewpager()
        tabLayout = findViewById(R.id.tl_tab_layout)
        tabLayout.setupWithViewPager(viewpager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager.currentItem = tab.position
            }
        })
    }

    private fun setupViewpager() {
        val fragmentList = listOf<Fragment>(TrackListFragment.instance, ArtistListFragment.instance)
        viewpager.adapter = TablListAdapter(supportFragmentManager, fragmentList)
    }

    class TablListAdapter(fragmentManager: FragmentManager, val fragmentList: List<Fragment>) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = fragmentList[position]
        override fun getCount(): Int = fragmentList.size
        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Track"
                1 -> "Artist"
                else -> ""
            }
        }
    }

}