package com.jhurtado.lastfm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhurtado.lastfm.data.model.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArtist(tracks: List<Artist>)

    @Query("SELECT * FROM artists where name like :searchQuery  limit :pagesize offset :initial")
    fun getArtists(pagesize: Int, initial: Int, searchQuery: String = ""): List<Artist>
}