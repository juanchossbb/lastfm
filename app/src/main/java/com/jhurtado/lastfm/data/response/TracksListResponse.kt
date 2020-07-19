package com.jhurtado.lastfm.data.response

import com.google.gson.annotations.SerializedName
import com.jhurtado.lastfm.data.model.Attributes
import com.jhurtado.lastfm.data.model.Track

class TracksListResponse {
    lateinit var tracks: TracksResponseObject
}

class TracksResponseObject {
    lateinit var track: MutableList<Track>

    @SerializedName("@attr")
    lateinit var attributes: Attributes
}