package com.jhurtado.lastfm.ui.artistlist.domain

import com.jhurtado.lastfm.UseCase
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.source.DataSource

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class SaveArtistList (private val dataSource: DataSource, private val artistList: List<Artist>) : UseCase<SaveArtistList.RequestValues, SaveArtistList.ResponseValue>() {
    class RequestValues : UseCase.RequestValues
    class ResponseValue(val wasSaved: Boolean) : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        dataSource.saveArtistList(artistList, object : DataSource.SaveCallback{
            override fun onDataSaved() {
                useCaseCallback?.onSuccess(ResponseValue(true))
            }

            override fun onError(t: Throwable) {
                useCaseCallback?.onError(Throwable("No ha podido guardarse en base de datos"))
            }


        })
    }
}