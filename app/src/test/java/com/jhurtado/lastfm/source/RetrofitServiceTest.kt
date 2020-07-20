package com.jhurtado.lastfm.source

import com.jhurtado.lastfm.BaseUnitTest
import com.jhurtado.lastfm.data.RetrofitService
import com.jhurtado.lastfm.data.response.ArtistListResponse
import com.jhurtado.lastfm.data.response.TracksListResponse
import junit.framework.Assert.*
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class RetrofitServiceTest : BaseUnitTest() {
    val webMockServer = MockWebServer()
    lateinit var service: RetrofitService

    override fun setUp() {
        webMockServer.start()
        service = Retrofit.Builder()
            .baseUrl(webMockServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RetrofitService::class.java)
    }

    @After
    fun tearDown() {
        webMockServer.shutdown()
    }

    @Test
    fun getTracksTest() {
        var response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(trackResponse)
        webMockServer.enqueue(response)

        var result = service.getTracks(1, 10).blockingFirst()
        assertNotNull(result)
        assertTrue(result is TracksListResponse)
        assertEquals(2, result.tracks.track.size)
        assertEquals("Creep", result.tracks.track[0].name)
        assertEquals("239", result.tracks.track[0].duration)
        assertEquals("1740708", result.tracks.track[0].listeners)
        assertEquals("d11fcceb-dfc5-4d19-b45d-f4e8f6d3eaa6", result.tracks.track[0].mbid)
        assertTrue(result.tracks.track[0].image.isNotEmpty())

        assertEquals("Colombia", result.tracks.attributes.country)
        assertEquals(3637492, result.tracks.attributes.totalPages)
        assertEquals(7274983, result.tracks.attributes.total)
    }

    @Test
    fun getArtistsTest() {
        var response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(artistResponse)
        webMockServer.enqueue(response)

        var result = service.getArtists(1, 10).blockingFirst()
        assertNotNull(result)
        assertTrue(result is ArtistListResponse)
        assertEquals(2, result.topartists.artist.size)
        assertEquals("David Bowie", result.topartists.artist[0].name)
        assertEquals("3518689", result.topartists.artist[0].listeners)
        assertEquals("5441c29d-3602-4898-b1a1-b77fa23b8e50", result.topartists.artist[0].mbid)
        assertTrue(result.topartists.artist[0].image.isNotEmpty())

        assertEquals("Spain", result.topartists.attributes.country)
        assertEquals(414601, result.topartists.attributes.totalPages)
        assertEquals(829201, result.topartists.attributes.total)

    }

    private val trackResponse =
        "{\"tracks\":{\"track\":[{\"name\":\"Creep\",\"duration\":\"239\",\"listeners\":\"1740708\",\"mbid\":\"d11fcceb-dfc5-4d19-b45d-f4e8f6d3eaa6\",\"url\":\"https://www.last.fm/music/Radiohead/_/Creep\",\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"},\"artist\":{\"name\":\"Radiohead\",\"mbid\":\"a74b1b7f-71a5-4011-9441-d0b5e4122711\",\"url\":\"https://www.last.fm/music/Radiohead\"},\"image\":[{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"extralarge\"}],\"@attr\":{\"rank\":\"0\"}},{\"name\":\"The Less I Know the Better\",\"duration\":\"0\",\"listeners\":\"594743\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tame+Impala/_/The+Less+I+Know+the+Better\",\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"},\"artist\":{\"name\":\"Tame Impala\",\"mbid\":\"63aa26c3-d59b-4da4-84ac-716b54f1ef4d\",\"url\":\"https://www.last.fm/music/Tame+Impala\"},\"image\":[{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"extralarge\"}],\"@attr\":{\"rank\":\"1\"}}],\"@attr\":{\"country\":\"Colombia\",\"page\":\"1\",\"perPage\":\"2\",\"totalPages\":\"3637492\",\"total\":\"7274983\"}}}"
    private val artistResponse =
        "{\"topartists\":{\"artist\":[{\"name\":\"David Bowie\",\"listeners\":\"3518689\",\"mbid\":\"5441c29d-3602-4898-b1a1-b77fa23b8e50\",\"url\":\"https://www.last.fm/music/David+Bowie\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"mega\"}]},{\"name\":\"Radiohead\",\"listeners\":\"4899921\",\"mbid\":\"a74b1b7f-71a5-4011-9441-d0b5e4122711\",\"url\":\"https://www.last.fm/music/Radiohead\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png\",\"size\":\"mega\"}]}],\"@attr\":{\"country\":\"Spain\",\"page\":\"1\",\"perPage\":\"2\",\"totalPages\":\"414601\",\"total\":\"829201\"}}}"
}