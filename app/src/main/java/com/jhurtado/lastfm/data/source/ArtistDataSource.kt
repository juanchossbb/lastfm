package com.jhurtado.lastfm.data.source

import androidx.paging.PositionalDataSource
import com.jhurtado.lastfm.ApplicationDelegate
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.utils.provideAppDatabase
import com.jhurtado.lastfm.utils.provideRetrofitService

private val database = provideAppDatabase()
private val service = provideRetrofitService()
var searchQuery = ""

class ArtistDataSource : PositionalDataSource<Artist>() {
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Artist>) {
        callback.onResult(loadRangeInternal(params.startPosition, params.loadSize))
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Artist>) {
        callback.onResult(loadRangeInternal(0, params.pageSize), params.requestedStartPosition)
    }

    private fun loadRangeInternal(start: Int, count: Int): List<Artist> {
        if (ApplicationDelegate.connectedToInternet()) {
            database.artistDao().insertArtist(
                service.getArtists((start / count) + 1, count).doOnError {
                    it.printStackTrace()
                }.blockingFirst().topartists.artist
            )
        }
        return database.artistDao().getArtists(count, start, "%$searchQuery%")
    }
}