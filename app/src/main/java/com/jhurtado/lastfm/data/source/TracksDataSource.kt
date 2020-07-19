package com.jhurtado.lastfm.data.source

import androidx.paging.PositionalDataSource
import com.jhurtado.lastfm.ApplicationDelegate
import com.jhurtado.lastfm.data.RetrofitFactory
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.utils.provideAppDatabase

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class TracksDataSource : PositionalDataSource<Track>() {
    val database = provideAppDatabase()
    val service by lazy { RetrofitFactory.getRetrofitService() }
    var searchQuery = ""

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Track>
    ) {
        callback.onResult(loadRangeInternal(params.startPosition, params.loadSize))
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Track>
    ) {
        callback.onResult(loadRangeInternal(0, params.pageSize), 0)
    }

    private fun loadRangeInternal(start: Int, count: Int): List<Track> {
        if (ApplicationDelegate.connectedToInternet()) {
            database.tracksDao().insertTracks(
                service.getTracks((start / count) + 1, count).blockingFirst().tracks.track
            )
        }
        return database.tracksDao().getTracks(count, start, "%$searchQuery%")
    }
}