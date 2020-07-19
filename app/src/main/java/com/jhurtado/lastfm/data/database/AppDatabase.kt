package com.jhurtado.lastfm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jhurtado.lastfm.data.database.dao.ArtistDao
import com.jhurtado.lastfm.data.database.dao.TracksDao
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Track

@Database(entities = [Track::class, Artist::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tracksDao(): TracksDao
    abstract fun artistDao(): ArtistDao
}