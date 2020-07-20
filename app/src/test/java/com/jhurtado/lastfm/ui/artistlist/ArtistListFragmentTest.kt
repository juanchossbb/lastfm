package com.jhurtado.lastfm.ui.tracklist

import androidx.fragment.app.Fragment
import com.jhurtado.lastfm.BaseFragmentTest
import com.jhurtado.lastfm.TestUtils
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.ui.artistlist.ArtistListFragment
import com.jhurtado.lastfm.ui.artistlist.ArtistListViewModel
import junit.framework.Assert.*
import org.junit.Test


/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class ArtistListFragmentTest : BaseFragmentTest<ArtistListFragment>() {

    override fun setFragment(): Fragment {
        return ArtistListFragment.instance
    }

    @Test
    fun testFragmentViews() {
        assertNotNull(fragment)
        assertNotNull(fragment?.fragmentTitle)
        assertEquals("Artists", fragment?.fragmentTitle?.text.toString())
        assertNotNull(fragment?.viewModel)
        assertTrue(fragment?.viewModel is ArtistListViewModel)
        assertNotNull(fragment?.recyclerview)
    }

    @Test
    fun testShowTrackList() {
        fragment?.showArtistList(TestUtils.mockPagedList(listOf()))
        assertEquals(0, fragment?.recyclerview?.adapter?.itemCount)

        var mocklist = TestUtils.mockList<Artist>(20)
        fragment?.showArtistList(TestUtils.mockPagedList(mocklist))
        assertEquals(20, fragment?.recyclerview?.adapter?.itemCount)
    }

}