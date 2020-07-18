package com.jhurtado.lastfm.data.source

import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.BaseObject
import com.jhurtado.lastfm.data.model.Track

/**
 * @author jhurtado
 * Date: 17/07/20
 */
interface DataSource {
    interface LoadCallback {
        fun onDataLoaded(data: List<BaseObject>)
        fun onError(t: Throwable)
    }

    interface SaveCallback {
        fun onDataSaved()
        fun onError(t: Throwable)
    }

    fun getArtistList(callback: LoadCallback)
    fun saveArtistList(list: List<Artist>, callback: SaveCallback)
    fun getTrackList(callback: LoadCallback)
    fun saveTracksList(list: List<Track>, callback: SaveCallback)

}
