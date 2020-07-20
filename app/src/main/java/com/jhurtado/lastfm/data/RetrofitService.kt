package com.jhurtado.lastfm.data

import com.jhurtado.lastfm.data.response.ArtistListResponse
import com.jhurtado.lastfm.data.response.TracksListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

private const val URL = "2.0/?country=colombia&api_key=829751643419a7128b7ada50de590067&format=json"
private const val TRACKS_METHOD = "geo.gettoptracks"
private const val ARTIST_METHOD = "geo.gettopartists"
interface RetrofitService {

    @GET(URL)
    fun getTracks(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("method") method: String = TRACKS_METHOD
    ): Observable<TracksListResponse>

    @GET(URL)
    fun getArtists(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("method") method: String = ARTIST_METHOD
    ): Observable<ArtistListResponse>
}