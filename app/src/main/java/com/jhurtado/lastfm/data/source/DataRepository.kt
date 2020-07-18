package com.jhurtado.lastfm.data.source

import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Track

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
class DataRepository private constructor(
    private val localDataRepository : DataSource,
    private val remoteDatasource : DataSource
) : DataSource {
    override fun getArtistList(callback: DataSource.LoadCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveArtistList(list: List<Artist>, callback: DataSource.SaveCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackList(callback: DataSource.LoadCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTracksList(list: List<Track>, callback: DataSource.SaveCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private var instance : DataRepository? = null
        fun getInstance(localDataSource : DataSource, remoteDatasource: DataSource) : DataRepository{
            if (instance == null){
                instance = DataRepository(localDataSource,remoteDatasource)
            }
            return instance!!
        }
    }
}