package com.jhurtado.lastfm.ui.tracklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.source.TracksDatasourceFactory

private const val PAGE_SIZE = 50
class TrackListViewModel(val fragment: TrackListFragment) : ViewModel() {
    lateinit var trackList: LiveData<PagedList<Track>>

    init {
        loadTracksFromDatabase()
    }

    private fun loadTracksFromDatabase() {
        val config = PagedList.Config.Builder()
            .setPrefetchDistance(PAGE_SIZE / 2)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        val factory = TracksDatasourceFactory()
        trackList = LivePagedListBuilder(factory, config).build()
        trackList.observe(fragment.activity!!, Observer {
            fragment.showTrackList(it)
        })
    }
}