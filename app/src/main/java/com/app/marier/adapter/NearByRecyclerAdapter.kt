package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.R

class NearByRecyclerAdapter(fragmentActivity: FragmentActivity) :RecyclerView.Adapter<NearByRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearByRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nearbyrecycleritem, parent, false)
        return NearByRecyclerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

            return 4


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {





    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var imggallery = itemView.findViewById<ImageView>(R.id.imggallery)
        var imgadd = itemView.findViewById<ImageView>(R.id.imgadd)


    }
}