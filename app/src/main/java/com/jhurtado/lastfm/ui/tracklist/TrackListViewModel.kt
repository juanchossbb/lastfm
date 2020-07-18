package com.jhurtado.lastfm.ui.tracklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.response.TracksListResponse
import com.jhurtado.lastfm.data.source.local.TracksDatasourceFactory
import com.jhurtado.lastfm.utils.provideDataRepository

class TrackListViewModel(val fragment: TrackListFragment) : ViewModel() {
    val tracks: MutableLiveData<TracksListResponse> = MutableLiveData()
    lateinit var trackList: LiveData<PagedList<Track>>
    private val repository by lazy { provideDataRepository() }

    init {
        loadTracks()
    }

    private fun loadTracks() {
        repository.getTrackList(tracks)
        val config = PagedList.Config.Builder().setPageSize(20).build()
        val factory = TracksDatasourceFactory()
        tracks.observeForever {
            trackList = LivePagedListBuilder(factory, config).build()
            trackList.observe(fragment.activity!!, Observer {
                fragment.showTrackList(it)
            })
        }

    }
}