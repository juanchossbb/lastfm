package com.jhurtado.lastfm.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.jhurtado.lastfm.data.RetrofitFactory
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.response.TracksListResponse
import com.jhurtado.lastfm.data.source.DataSource

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class RemoteDataSource : DataSource {
    val service by lazy { RetrofitFactory.getRetrofitService() }
    override fun getArtistList(callback: DataSource.LoadCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveArtistList(list: List<Artist>, callback: DataSource.SaveCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackList(liveData: MutableLiveData<TracksListResponse>) {
        service.getTracks().subscribe {
            liveData.postValue(it)
        }
    }

    override fun saveTracksList(list: Array<Track>) {}

    companion object {
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource {
            if (instance == null) {
                instance = RemoteDataSource()
            }
            return instance!!
        }
    }

}