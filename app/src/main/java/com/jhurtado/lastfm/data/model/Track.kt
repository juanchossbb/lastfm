package com.jhurtado.lastfm.data.model

import androidx.room.Entity

/**
 * @author jhurtado
 * Date: 17/07/20
 * Time: 10:03 PM
 */
@Entity
class Track : BaseObject() {
    lateinit var duration: String
    lateinit var artist: Artist
    lateinit var image: Array<Image>
}