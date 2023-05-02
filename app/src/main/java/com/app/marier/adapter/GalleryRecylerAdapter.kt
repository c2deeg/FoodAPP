package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Models.GetCurrentUser.Gallery
import com.app.marier.R
import com.bumptech.glide.Glide

class GalleryRecylerAdapter(private val activity: FragmentActivity,private val gallery: List<Gallery>) : RecyclerView.Adapter<GalleryRecylerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryRecylerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.galleryrecycleritem, parent, false)
        return GalleryRecylerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return gallery.size


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(activity)
            .load(gallery.get(position).image).placeholder(R.drawable.rock)
            .into(holder.imguuser!!)

    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var imguuser = itemView.findViewById<ImageView>(R.id.imguuser)


    }
}