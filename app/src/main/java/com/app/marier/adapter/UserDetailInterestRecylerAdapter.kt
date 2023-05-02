package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Models.GetCurrentUser.Interest
import com.app.marier.R

class UserDetailInterestRecylerAdapter(
    private val activity: FragmentActivity,
   private val interests: List<Interest>
) : RecyclerView.Adapter<UserDetailInterestRecylerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDetailInterestRecylerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.userinterestrecyleritem, parent, false)
        return UserDetailInterestRecylerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return interests.size


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvinterset.text=interests.get(position).type


    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tvinterset = itemView.findViewById<TextView>(R.id.tvinterset)


    }
}