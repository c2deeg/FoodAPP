package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.R

class FriendsRecyclerAdapter (private val activity: FragmentActivity) : RecyclerView.Adapter<FriendsRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friendsrecycleritem, parent, false)
        return FriendsRecyclerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return 4


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


    }
}