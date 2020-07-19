package com.jhurtado.lastfm.ui.artistlist

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
import com.jhurtado.lastfm.data.model.Artist
import com.jhurtado.lastfm.utils.PicassoCircleTransform
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class ArtistListAdapter :
    PagedListAdapter<Artist, ArtistListAdapter.ArtistListViewHolder>(Artist.DIFF_CALLBACK) {

    class ArtistListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage = view.findViewById<ImageView>(R.id.iv_artist_item)
        val tvName = view.findViewById<TextView>(R.id.tv_artist_name)
        val tvListeners = view.findViewById<TextView>(R.id.tv_artist_listeners_value)
        val btnDetails = view.findViewById<Button>(R.id.btn_artist_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistListViewHolder {
        return ArtistListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.artist_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistListViewHolder, position: Int) {
        val artist = getItem(position)
        holder.tvName.text = artist?.name
        holder.tvListeners.text =
            NumberFormat.getNumberInstance(Locale.US).format(artist?.listeners?.toInt())
        holder.btnDetails.tag = artist?.url
        holder.btnDetails.setOnClickListener(artistListClickListener)
        Picasso.get().load(artist?.image?.get(0)?.text).transform(PicassoCircleTransform()).fit()
            .into(holder.ivImage)
    }

    private val artistListClickListener = View.OnClickListener {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.tag as? String))
        it.context.startActivity(browserIntent)
    }
}