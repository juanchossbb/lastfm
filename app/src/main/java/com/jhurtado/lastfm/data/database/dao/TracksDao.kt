package com.jhurtado.lastfm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jhurtado.lastfm.data.response.TracksListResponse

@Dao
interface TracksDao {
    @Insert
    fun insertTracks(tracks: TracksListResponse)

    @Query("SELECT * FROM tracks")
    fun getTracks(): TracksListResponse
}