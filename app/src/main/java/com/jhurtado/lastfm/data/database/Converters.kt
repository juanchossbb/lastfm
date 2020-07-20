package com.jhurtado.lastfm.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Attributes
import com.jhurtado.lastfm.data.model.Image
import com.jhurtado.lastfm.data.model.Track
import com.jhurtado.lastfm.data.response.TracksResponseObject

class Converters {
    private val gson by lazy { Gson() }

    @TypeConverter
    fun fromArtistToString(artist: Artist): String {
        return gson.toJson(artist)
    }

    @TypeConverter
    fun fromStringToArtist(json: String): Artist {
        return gson.fromJson(json, Artist::class.java)
    }

    @TypeConverter
    fun fromImageToString(images: Array<Image>): String {
        return gson.toJson(images)
    }

    @TypeConverter
    fun fromStringToImage(json: String): Array<Image> {
        return gson.fromJson(json, Array<Image>::class.java)
    }

    @TypeConverter
    fun fromTrackListToString(track: Array<Track>): String {
        return gson.toJson(track)
    }

    @TypeConverter
    fun fromStringToTrackList(json: String): Array<Track> {
        return gson.fromJson(json, Array<Track>::class.java)
    }

    @TypeConverter
    fun fromAttributesToString(attributes: Attributes): String {
        return gson.toJson(attributes)
    }

    @TypeConverter
    fun froStringtoAttributes(json: String): Attributes {
        return gson.fromJson(json, Attributes::class.java)
    }

    @TypeConverter
    fun fromTrackResponseObjectToSrting(trackobjec: TracksResponseObject): String {
        return gson.toJson(trackobjec)
    }

    @TypeConverter
    fun fromStringToTrackResponseObject(json: String): TracksResponseObject {
        return gson.fromJson(json, TracksResponseObject::class.java)
    }
}