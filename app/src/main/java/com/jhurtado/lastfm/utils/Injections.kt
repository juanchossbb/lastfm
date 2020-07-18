package com.jhurtado.lastfm.utils

import com.jhurtado.lastfm.UseCaseHandler
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.source.DataRepository
import com.jhurtado.lastfm.data.source.DataSource
import com.jhurtado.lastfm.data.source.local.LocalDataSource
import com.jhurtado.lastfm.data.source.remote.RemoteDataSource
import com.jhurtado.lastfm.ui.artistlist.domain.GetArtistList
import com.jhurtado.lastfm.ui.artistlist.domain.SaveArtistList
import com.jhurtado.lastfm.ui.tracklist.domain.GetTrackList
import com.jhurtado.lastfm.ui.tracklist.domain.SaveTrackList

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
fun providePostDataRepository(): DataRepository {
    return DataRepository.getInstance(provideLocalDataSource(), provideRemoteDataSource())
}

fun provideViewModelFactory() = ViewModelFactory.getInstance()

fun provideLocalDataSource(): DataSource = LocalDataSource.getInstance()

fun provideRemoteDataSource(): DataSource = RemoteDataSource.getInstance()

fun provideUseCaseHandler() = UseCaseHandler.getInstance()

fun provideGetArtistList() = GetArtistList(providePostDataRepository())

fun provideGetTrackList() = GetTrackList(providePostDataRepository())

fun provideSaveArtistList(list : List<Artist>) = SaveArtistList(providePostDataRepository(), list)

fun provideSaveTrackList(list : List<Track>) = SaveTrackList(providePostDataRepository(), list)