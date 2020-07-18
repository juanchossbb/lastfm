package com.jhurtado.lastfm.data.source.local

import androidx.lifecycle.MutableLiveData
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.response.TracksListResponse
import com.jhurtado.lastfm.data.source.DataSource
import com.jhurtado.lastfm.utils.provideAppDatabase

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class LocalDataSource  : DataSource {
    val database = provideAppDatabase()
    override fun getArtistList(callback: DataSource.LoadCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveArtistList(list: List<Artist>, callback: DataSource.SaveCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackList(livedata: MutableLiveData<TracksListResponse>) {
        livedata.postValue(database.tracksDao().getTracks())
    }


    override fun saveTracksList(list: List<Track>, callback: DataSource.SaveCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        private var instance: LocalDataSource? = null
        fun getInstance() : LocalDataSource{
            if(instance == null){
                instance = LocalDataSource()
            }
            return instance!!
        }
    }

}