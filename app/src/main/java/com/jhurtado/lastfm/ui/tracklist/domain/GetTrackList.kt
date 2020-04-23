package com.jhurtado.lastfm.ui.tracklist.domain

import com.jhurtado.lastfm.UseCase
import com.jhurtado.lastfm.data.model.BaseObject
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.source.DataSource

/**
 * @author jhurtado
 * Date: 22/04/20
 * LasfFM test for Valid.com
 */

class GetTrackList (private val dataSource: DataSource) : UseCase<GetTrackList.RequestValues, GetTrackList.ResponseValue>() {
    class RequestValues : UseCase.RequestValues
    class ResponseValue(val trackList: List<Track>) : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        dataSource.getTrackList(object : DataSource.LoadCallback{
            override fun onDataLoaded(data: List<BaseObject>) {
                val trackList : List<Track> = data as List<Track>
                val responseValue = ResponseValue(trackList)
                useCaseCallback?.onSuccess(responseValue)
            }

            override fun onError(t: Throwable) {
                useCaseCallback?.onError(Throwable("No se encontraron datos"))
            }

        })
    }
}