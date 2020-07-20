package com.jhurtado.lastfm.ui.artistlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jhurtado.lastfm.R
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.ui.BaseListFragment
import com.jhurtado.lastfm.ui.ListTabActivity

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class ArtistListFragment : BaseListFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTitle.setText(R.string.artist_list_fragment_title)
        viewModel = ArtistListViewModel(this)

        (activity as? ListTabActivity)?.searchLiveData?.observe(viewLifecycleOwner, Observer {
            (recyclerview.adapter as ArtistListAdapter).submitList(null)
            (viewModel as ArtistListViewModel).searchArtists(it)
        })
    }

    fun showArtistList(artistlist: PagedList<Artist>) {
        recyclerview.adapter = ArtistListAdapter().apply { submitList(artistlist) }
    }

    companion object {
        val instance: ArtistListFragment = ArtistListFragment()
    }
}