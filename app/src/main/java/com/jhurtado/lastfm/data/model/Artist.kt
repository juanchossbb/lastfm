package com.jhurtado.lastfm.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import com.google.gson.Gson

/**
 * @author jhurtado
 * Date: 17/07/20
 * Time: 10:02 PM
 */
@Entity(tableName = "artists")
class Artist : BaseObject() {

    companion object {
        private val gson by lazy { Gson() }
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Artist> =
            object : DiffUtil.ItemCallback<Artist>() {
                override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
                    return oldItem.mbid == newItem.mbid
                }

                override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
                    return gson.toJson(oldItem) == gson.toJson(newItem)
                }
            }
    }
}

