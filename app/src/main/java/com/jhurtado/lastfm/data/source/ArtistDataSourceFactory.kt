package com.jhurtado.lastfm.data.source

import androidx.paging.DataSource
import com.jhurtado.lastfm.data.model.Artist

class ArtistDataSourceFactory : DataSource.Factory<Int, Artist>() {
    val artitstDataSource = ArtistDataSource()
    var search = ""
    override fun create(): DataSource<Int, Artist> {
        return artitstDataSource.apply { searchQuery = search }
    }

    fun search(query: String) {
        search = query
    }
}