package com.jhurtado.lastfm.data.model

import com.google.gson.annotations.SerializedName

class Image {
    @SerializedName("#text")
    lateinit var text: String
    lateinit var size: ImageSize
}

enum class ImageSize {
    small, medium, large, extralarge
}