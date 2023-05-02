package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Models.GetLikesModel.Data
import com.app.marier.R

class LikeRecyclerAdapter(private val activity: FragmentActivity,private val data: List<Data>) : RecyclerView.Adapter<LikeRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LikeRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.likerecycleradapter, parent, false)
        return LikeRecyclerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return data.size


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvname.text = data.get(position).likeBy.name
        if (position==1){
            holder.imguser.setImageResource(R.drawable.menssss)
        }


    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var imguser = itemView.findViewById<ImageView>(R.id.imguser)
        var tvname = itemView.findViewById<TextView>(R.id.tvname)


    }

}