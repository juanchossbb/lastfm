package com.jhurtado.lastfm.ui.tracklist.domain

import com.jhurtado.lastfm.UseCase
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.source.DataSource

/**
 * @author jhurtado
 * Date: 22/04/20
 * LasfFM test for Valid.com
 */

class SaveTrackList (private val dataSource: DataSource, private val trackList : List<Track>) : UseCase<SaveTrackList.RequestValues
        , SaveTrackList.ResponseValue>() {
    class RequestValues : UseCase.RequestValues
    class ResponseValue(val wasSaved: Boolean) : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        dataSource.saveTracksList(trackList, object : DataSource.SaveCallback{
            override fun onDataSaved() {
                useCaseCallback?.onSuccess(ResponseValue(true))
            }

            override fun onError(t: Throwable) {
               useCaseCallback?.onError(Throwable("No ha podido guardarse en base de datos"))
            }

        })
    }


}