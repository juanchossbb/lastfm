package com.jhurtado.lastfm.ui.artistlist.domain

import com.jhurtado.lastfm.UseCase
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.BaseObject
import com.jhurtado.lastfm.data.source.DataSource

/**
 * @author jhurtado
 * Date: 22/04/20
 * LasfFM test for Valid.com
 */

class GetArtistList (private val dataSource: DataSource) :UseCase<GetArtistList.RequestValues, GetArtistList.ResponseValue>() {

    class RequestValues : UseCase.RequestValues
    class ResponseValue(val artistList: List<Artist>) : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        dataSource.getArtistList(object  : DataSource.LoadCallback{
            override fun onDataLoaded(data: List<BaseObject>) {
                val artistList : List<Artist> = data as List<Artist>
                val responseValue = ResponseValue(artistList)
                useCaseCallback?.onSuccess(responseValue)
            }

            override fun onError(t: Throwable) {
                useCaseCallback?.onError(Throwable("No se encontraron datos"))
            }

        })
    }

}