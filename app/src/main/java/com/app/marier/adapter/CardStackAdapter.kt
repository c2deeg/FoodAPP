package com.app.marier.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Models.GetRandomList.Data
import com.app.marier.R
import com.app.marier.activities.UserDetailActivity.UserDetailActivity
import com.bumptech.glide.Glide
import com.yuyakaido.android.cardstackview.CardStackLayoutManager

class CardStackAdapter(private val activity: FragmentActivity, private var data: List<Data>) :
    RecyclerView.Adapter<CardStackAdapter.ViewHolder>(), View.OnClickListener {
    private lateinit var manager: CardStackLayoutManager


    private var viewClick: OnViewClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_name.text = data.get(position).name
        Glide.with(activity)
            .load(data.get(position).gallery.get(0).image).placeholder(R.drawable.rock)
            .into(holder.img_user!!)
        holder.relativelay.setOnClickListener {
            var intent = Intent(activity, UserDetailActivity::class.java)
            intent.putExtra("userid",data.get(position)._id)
            activity.startActivity(intent)
        }


    }

    public interface OnViewClick {

        fun onitemclick(position: Int)
    }

    fun ViewClickListner(click: OnViewClick) {
        this.viewClick = click
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setSpots(spots: ArrayList<Data>) {
        this.data = spots
    }
//
    fun getSpots(): List<Data> {
        return data
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img_user: ImageView = view.findViewById(R.id.img_user)
        val tv_name: TextView = view.findViewById(R.id.tv_name)
        var tv_distance: TextView = view.findViewById(R.id.tv_distance)
        var relativelay: RelativeLayout = view.findViewById(R.id.relativelay)


    }

    override fun onClick(v: View?) {


    }
}