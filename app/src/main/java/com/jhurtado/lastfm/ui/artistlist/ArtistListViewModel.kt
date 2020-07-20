package com.jhurtado.lastfm.ui.artistlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.source.ArtistDataSourceFactory

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
private const val PAGE_SIZE = 50

class ArtistListViewModel(private val fragment: ArtistListFragment) : ViewModel() {
    lateinit var artistList: LiveData<PagedList<Artist>>
    val factory by lazy { ArtistDataSourceFactory() }

    init {
        loadArtistsFromDatabase()
    }

    private fun loadArtistsFromDatabase() {
        val config = PagedList.Config.Builder()
            .setPrefetchDistance(PAGE_SIZE / 2)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        artistList = LivePagedListBuilder(factory, config).build()
        artistList.observe(fragment, Observer {
            fragment.showArtistList(it)
        })
    }

    fun searchArtists(query: String) {
        factory.search(query)
        loadArtistsFromDatabase()
        artistList.value?.dataSource?.invalidate()
    }
}