package com.jhurtado.lastfm.ui.tracklist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jhurtado.lastfm.data.response.TracksListResponse
import com.jhurtado.lastfm.utils.provideDataRepository

class TrackListViewModel(val fragment: TrackListFragment) : ViewModel() {
    val tracks: MutableLiveData<TracksListResponse> = MutableLiveData()
    private val repository by lazy { provideDataRepository(fragment.activity!!) }

    init {
        loadTracks()
    }

    private fun loadTracks() {
        repository.getTrackList(tracks)
        tracks.observe(fragment, Observer {
            fragment.showTracksList(it.tracks.track.toList())
        })
    }
}