package com.jhurtado.lastfm.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhurtado.lastfm.ui.artistlist.ArtistListViewModel

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ArtistListViewModel::javaClass) {
            return ArtistListViewModel(
                provideUseCaseHandler(), provideGetArtistList(), provideSaveArtistList(emptyList())
            ) as T
        } else throw IllegalArgumentException("unknown model class $modelClass")
    }

    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory {
            if (instance == null) {
                instance = ViewModelFactory()
            }
            return instance!!
        }
    }

}