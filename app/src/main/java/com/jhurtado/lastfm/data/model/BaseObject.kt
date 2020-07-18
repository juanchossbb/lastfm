package com.jhurtado.lastfm.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
@Entity
open class BaseObject() {
    lateinit var name: String


    @PrimaryKey
    lateinit var mbid : String
    lateinit var url: String
    lateinit var listeners: String

    @Ignore
    lateinit var streamabale: Integer
}