package com.jhurtado.lastfm.data.source

import androidx.paging.DataSource
import com.jhurtado.lastfm.data.model.Track

class TracksDatasourceFactory : DataSource.Factory<Int, Track>() {
    override fun create(): DataSource<Int, Track> {
        return TracksDataSource()
    }

}