package com.jhurtado.lastfm.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.MutableLiveData
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
open class ListTabActivity : AppCompatActivity() {
    lateinit var viewpager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var searchLiveData: MutableLiveData<String>

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
        searchLiveData = MutableLiveData()
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchAction(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchAction(newText)
                return true
            }

        })
        menu.findItem(R.id.search).isVisible = true
        return true
    }


    private fun handleIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY) ?: ""
            searchAction("%$query%")
        }
    }

    @VisibleForTesting
    fun searchAction(query: String) {
        searchLiveData.postValue(query)
        viewpager.adapter?.notifyDataSetChanged()
    }

    @VisibleForTesting
    fun setupViewpager() {
        val fragmentList = listOf<Fragment>(TrackListFragment.instance, ArtistListFragment.instance)
        viewpager.adapter = TablListAdapter(supportFragmentManager, fragmentList)
    }

    class TablListAdapter(fragmentManager: FragmentManager, val fragmentList: List<Fragment>) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = fragmentList[position]
        override fun getCount(): Int = fragmentList.size
        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Tracks"
                1 -> "Artists"
                else -> ""
            }
        }
    }

}