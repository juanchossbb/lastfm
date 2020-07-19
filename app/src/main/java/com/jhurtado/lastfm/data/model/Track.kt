package com.jhurtado.lastfm.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import com.google.gson.Gson

/**
 * @author jhurtado
 * Date: 17/07/20
 * Time: 10:03 PM
 */
@Entity(tableName = "tracks")
class Track : BaseObject() {
    lateinit var duration: String
    lateinit var artist: Artist

    companion object {
        private val gson by lazy { Gson() }
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Track> = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem.mbid == newItem.mbid
            }

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
                return gson.toJson(oldItem) == gson.toJson(newItem)
            }
        }
    }
}