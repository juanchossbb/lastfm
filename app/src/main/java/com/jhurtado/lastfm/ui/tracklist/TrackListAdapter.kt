package com.jhurtado.lastfm.ui.tracklist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jhurtado.lastfm.R
import com.jhurtado.lastfm.data.model.Track
import com.squareup.picasso.Picasso


class TrackListAdapter() :
    PagedListAdapter<Track, TrackListAdapter.TrackListViewHolder>(Track.DIFF_CALLBACK) {


    class TrackListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageItem = view.findViewById<ImageView>(R.id.iv_track_item)
        val nameItem = view.findViewById<TextView>(R.id.tv_track_name)
        val durationItem = view.findViewById<TextView>(R.id.tv_track_duration_value)
        val listenersItem = view.findViewById<TextView>(R.id.tv_track_listeners_value)
        val artistItem = view.findViewById<TextView>(R.id.tv_track_artist_value)
        val artistButton = view.findViewById<Button>(R.id.btn_track_artist)
        val trackButton = view.findViewById<Button>(R.id.btn_track_details)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListViewHolder =
        TrackListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.track_list_item, parent, false)
        )

    override fun getItemCount(): Int = currentList?.size ?: 0

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) {
        val track = getItem(position)
        holder.nameItem.text = track?.name
        holder.durationItem.text = track?.duration
        holder.listenersItem.text = track?.listeners
        holder.artistItem.text = track?.artist?.name
        Picasso.get().load(track?.image?.get(0)?.text).fit().into(holder.imageItem)
        holder.artistButton.tag = track?.artist?.url
        holder.artistButton.setOnClickListener(trackListClickListener)
        holder.trackButton.tag = track?.url
        holder.trackButton.setOnClickListener(trackListClickListener)
    }

    private val trackListClickListener = View.OnClickListener {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.tag as? String))
        it.context.startActivity(browserIntent)
    }
}