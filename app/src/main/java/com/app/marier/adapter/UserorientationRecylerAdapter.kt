package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Models.GetCurrentUser.SexualOrientation
import com.app.marier.R

class UserorientationRecylerAdapter(
    private val activity: FragmentActivity,
   private val sexualOrientations: List<SexualOrientation>
) : RecyclerView.Adapter<UserorientationRecylerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserorientationRecylerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.userinterestrecyleritem, parent, false)
        return UserorientationRecylerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return sexualOrientations.size


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvinterset.text = sexualOrientations.get(position).type


    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tvinterset = itemView.findViewById<TextView>(R.id.tvinterset)


    }
}