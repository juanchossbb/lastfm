package com.jhurtado.lastfm.data

import com.jhurtado.lastfm.data.response.TracksListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

private const val URL = "2.0/?country=colombia&api_key=829751643419a7128b7ada50de590067&format=json"

interface RetrofitService {

    @GET(URL)
    fun getTracks(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("method") method: String = "geo.gettoptracks"
    ): Observable<TracksListResponse>
}