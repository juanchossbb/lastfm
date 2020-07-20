package com.jhurtado.lastfm.ui

import android.app.SearchManager
import android.content.Intent
import com.jhurtado.lastfm.BaseUnitTest
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
class ListTabActivityTest : BaseUnitTest() {
    lateinit var activity: ListTabActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(ListTabActivity::class.java).create().get()
    }

    @Test
    fun testActivityViews() {
        assertNotNull(activity.viewpager)
        assertNotNull(activity.tabLayout)
        assertNotNull(activity.searchLiveData)
    }

    @Test
    fun testSetupViewPager() {
        activity.viewpager.adapter.let {
            activity.setupViewpager()
            assertNotNull(it)
            assertTrue(it is ListTabActivity.TablListAdapter)
            assertEquals(it?.count, 2)
            assertEquals("Tracks", it?.getPageTitle(0))
            assertEquals("Artists", it?.getPageTitle(1))
        }

    }

    @Test
    fun testTabLayout() {
        assertEquals(activity.tabLayout.tabCount, 2)
        assertEquals("Tracks", activity.tabLayout.getTabAt(0)?.text)
        assertEquals("Artists", activity.tabLayout.getTabAt(1)?.text)
        activity.tabLayout.getTabAt(0)?.select()
        assertEquals(0, activity.viewpager.currentItem)
        activity.tabLayout.getTabAt(1)?.select()
        assertEquals(1, activity.viewpager.currentItem)
    }

    @Test
    fun testSearchAction() {
        assertNull(activity.searchLiveData.value)
        activity.searchAction("test 1")
        assertEquals("test 1", activity.searchLiveData.value)
        activity.searchAction("")
        assertEquals("", activity.searchLiveData.value)

        val intent = Intent(Intent.ACTION_SEARCH).apply { putExtra(SearchManager.QUERY, "test 2") }
        activity =
            Robolectric.buildActivity(ListTabActivity::class.java).create().newIntent(intent).get()
        assertEquals("%test 2%", activity.searchLiveData.value)
    }
}