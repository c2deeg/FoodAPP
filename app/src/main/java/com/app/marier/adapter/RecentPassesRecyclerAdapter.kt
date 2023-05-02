package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.R

class RecentPassesRecyclerAdapter(private val activity: FragmentActivity) : RecyclerView.Adapter<RecentPassesRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentPassesRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recentpassedrecycleritem, parent, false)
        return RecentPassesRecyclerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return 20


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


    }
}