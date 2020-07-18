package com.jhurtado.lastfm.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jhurtado.lastfm.data.model.Attributes
import com.jhurtado.lastfm.data.model.Track

@Entity
class TracksListResponse {
    @PrimaryKey
    lateinit var tracks: TracksResponseObject
}

class TracksResponseObject {
    lateinit var track: MutableList<Track>

    @SerializedName("@attr")
    lateinit var attributes: Attributes
}