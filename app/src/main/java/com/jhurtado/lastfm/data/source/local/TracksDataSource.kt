package com.jhurtado.lastfm.data.source.local

import androidx.paging.PositionalDataSource
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.utils.provideAppDatabase

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class TracksDataSource : PositionalDataSource<Track>() {
    val database = provideAppDatabase()


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
        callback.onResult(loadRangeInternal(0, params.pageSize), 0, params.requestedLoadSize)
    }

    private fun loadRangeInternal(start: Int, count: Int): List<Track> {

        return database.tracksDao().getTracks(count, start).toList()
    }
}