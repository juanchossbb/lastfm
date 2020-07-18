package com.jhurtado.lastfm.data.source

import android.app.Activity
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
    private val localDataRepository: DataSource,
    private val remoteDatasource: DataSource,
    private val activity: Activity
) : DataSource {
    override fun getArtistList(callback: DataSource.LoadCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveArtistList(list: List<Artist>, callback: DataSource.SaveCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackList(livedata: MutableLiveData<TracksListResponse>) {
        val localobserver = MutableLiveData<TracksListResponse>()
        localobserver.observeForever {
            if (it == null)
                GlobalScope.launch {
                    remoteDatasource.getTrackList(livedata)
                }
            else
                livedata.postValue(it)
        }
        GlobalScope.launch {
            localDataRepository.getTrackList(localobserver)
        }

    }


    override fun saveTracksList(list: List<Track>, callback: DataSource.SaveCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private var instance: DataRepository? = null
        fun getInstance(
            localDataSource: DataSource,
            remoteDatasource: DataSource,
            view: Activity
        ): DataRepository {
            if (instance == null) {
                instance = DataRepository(localDataSource, remoteDatasource, view)
            }
            return instance!!
        }
    }
}