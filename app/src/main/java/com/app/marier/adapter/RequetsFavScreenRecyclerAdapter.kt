package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.R

class RequetsFavScreenRecyclerAdapter (private val activity: FragmentActivity) : RecyclerView.Adapter<RequetsFavScreenRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequetsFavScreenRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.requestrecycleradapteritem, parent, false)
        return RequetsFavScreenRecyclerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return 4


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


    }
}