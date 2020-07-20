package com.jhurtado.lastfm.data.source

import androidx.paging.DataSource
import com.jhurtado.lastfm.data.model.Track

class TracksDatasourceFactory() : DataSource.Factory<Int, Track>() {
    val tracksDataSource = TracksDataSource()
    var search = ""
    override fun create(): DataSource<Int, Track> {
        return tracksDataSource.apply { searchQuery = search }
    }

    fun search(query: String) {
        search = query
    }
}