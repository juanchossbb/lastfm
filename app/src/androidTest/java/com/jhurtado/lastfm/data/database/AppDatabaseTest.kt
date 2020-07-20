package com.jhurtado.lastfm.data.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhurtado.lastfm.ApplicationDelegate
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Image
import com.jhurtado.lastfm.data.model.ImageSize
import com.jhurtado.lastfm.data.model.Track
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    lateinit var database: AppDatabase

    @Before
    fun initDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationDelegate.applicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun trackDatabaseTest() {
        var tracklist = database.tracksDao().getTracks(20, 0, "")
        assertEquals(0, tracklist.size)
        val insertlist = listOf<Track>(Track("500", Artist().apply {
            name = "Maluma"
            mbid = "id"
            url = "url"
            listeners = "12345"
            image = arrayOf(Image().apply { text = "000"; size = ImageSize.small })
        }).apply { name = "Felices los 4" })
        database.tracksDao().insertTracks(insertlist)
        tracklist = database.tracksDao().getTracks(20, 0, "")
        assertEquals(1, tracklist.size)
    }
}