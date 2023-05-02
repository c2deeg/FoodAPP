package com.app.marier.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.R
import com.app.marier.activities.ChatDeatilActivity.ChatDeatilActivity

class ChatListRecyclerAdapter (private val activity: FragmentActivity) :RecyclerView.Adapter<ChatListRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatListRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chatlistitem, parent, false)
        return ChatListRecyclerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return 4


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.linmain.setOnClickListener{
            var intent = Intent(activity,ChatDeatilActivity::class.java)
            activity.startActivity(intent)
        }


    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var linmain = itemView.findViewById<LinearLayout>(R.id.linmain)


    }
}