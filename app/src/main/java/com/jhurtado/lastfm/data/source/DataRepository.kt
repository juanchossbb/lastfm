package com.jhurtado.lastfm.data.source

import androidx.lifecycle.MutableLiveData
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.response.TracksListResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
class DataRepository private constructor(
    private val localDataSource: DataSource,
    private val remoteDatasource: DataSource
) : DataSource {
    override fun getArtistList(callback: DataSource.LoadCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveArtistList(list: List<Artist>, callback: DataSource.SaveCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackList(livedata: MutableLiveData<TracksListResponse>) {
        val observer = MutableLiveData<TracksListResponse>()
        observer.observeForever {
            GlobalScope.launch {
                saveTracksList(it.tracks.track.toTypedArray())
            }
            livedata.postValue(it)
        }
        GlobalScope.launch {
            remoteDatasource.getTrackList(observer)
        }
    }


    override fun saveTracksList(list: Array<Track>) {
        localDataSource.saveTracksList(list)
    }

    companion object {
        private var instance: DataRepository? = null
        fun getInstance(
            localDataSource: DataSource,
            remoteDatasource: DataSource
        ): DataRepository {
            if (instance == null) {
                instance = DataRepository(localDataSource, remoteDatasource)
            }
            return instance!!
        }
    }
}