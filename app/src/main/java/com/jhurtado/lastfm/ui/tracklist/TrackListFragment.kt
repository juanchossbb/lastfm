package com.jhurtado.lastfm.ui.tracklist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jhurtado.lastfm.R
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.ui.BaseListFragment
import com.jhurtado.lastfm.ui.ListTabActivity

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

        (activity as? ListTabActivity)?.searchLiveData?.observe(viewLifecycleOwner, Observer {
            (recyclerview.adapter as TrackListAdapter).submitList(null)
            (viewModel as TrackListViewModel).searchTracks(it)
        })
    }

    fun showTrackList(tracksList: PagedList<Track>) {
        recyclerview.adapter = TrackListAdapter().apply { submitList(tracksList) }
    }

    companion object {
        val instance: TrackListFragment = TrackListFragment()
    }
}