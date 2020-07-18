package com.jhurtado.lastfm.ui.tracklist

import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import com.jhurtado.lastfm.R
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.ui.BaseListFragment

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class TrackListFragment : BaseListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTitle.setText(R.string.track_list_fragment_title)
        viewModel = TrackListViewModel(this)
        viewpager.adapter = TrackListAdapter().apply { submitList(null) }
    }

    fun showTrackList(tracksList: PagedList<Track>) {
        viewpager.adapter = TrackListAdapter().apply { submitList(tracksList) }
    }

    companion object {
        val instance: TrackListFragment = TrackListFragment()
    }
}