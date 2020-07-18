package com.jhurtado.lastfm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jhurtado.lastfm.data.database.dao.TracksDao
import com.jhurtado.lastfm.data.response.TracksListResponse

@Database(entities = [TracksListResponse::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tracksDao(): TracksDao
}