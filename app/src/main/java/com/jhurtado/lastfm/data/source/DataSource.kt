package com.jhurtado.lastfm.data.source

import androidx.lifecycle.MutableLiveData
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.BaseObject
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.response.TracksListResponse

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
    fun getTrackList(livedata: MutableLiveData<TracksListResponse>)
    fun saveTracksList(list: Array<Track>)

}
