package com.jhurtado.lastfm.ui.tracklist

import android.os.Bundle
import android.view.View
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
        fragmentTitle.setText(R.string.artist_list_fragment_title)
        viewModel = TrackListViewModel(this)
    }

    fun showTracksList(tracksList: List<Track>) {
        viewpager.adapter = TrackListAdapter(tracksList)
    }

    companion object {
        val instance: TrackListFragment = TrackListFragment()
    }
}