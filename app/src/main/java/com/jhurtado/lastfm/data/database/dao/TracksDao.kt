package com.jhurtado.lastfm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhurtado.lastfm.data.model.Track

@Dao
interface TracksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTracks(tracks: List<Track>)

    @Query("SELECT * FROM tracks where name like :searchQuery  limit :pagesize offset :initial")
    fun getTracks(pagesize: Int, initial: Int, searchQuery: String = ""): List<Track>
}