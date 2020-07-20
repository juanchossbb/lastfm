package com.jhurtado.lastfm.data.response

import com.google.gson.annotations.SerializedName
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.data.model.Attributes

class ArtistListResponse(val topartists: ArtistListResponseObject)

class ArtistListResponseObject(
    val artist: List<Artist>,
    @SerializedName("@attr") val attributes: Attributes
)