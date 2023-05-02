package com.app.marier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Models.GetAllInterest.Data
import com.app.marier.R
import com.app.marier.`interface`.InterestRecyclerClicklistner

class ChipRecyclerAdapter(
    private val activity: FragmentActivity,
    private val data: List<Data>,
   private val clicklistner: InterestRecyclerClicklistner,
) : RecyclerView.Adapter<ChipRecyclerAdapter.ViewHolder>() {
    private var isselected:Boolean=false
    private var arrayList:ArrayList<String> = ArrayList()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChipRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemdata, parent, false)
        return ChipRecyclerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return data.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvinterset.text=data.get(position).type
        holder.tvinterset.setOnClickListener{
            if (isselected){
                arrayList.add(data.get(position)._id)
                holder.tvinterset.setBackgroundDrawable(activity.getDrawable(R.drawable.roundcornerstroke))
                holder.tvinterset.setTextColor(activity.getColor(R.color.black))
            }else{
//                arrayList.remove(data.get(position)._id)

                holder.tvinterset.setBackgroundDrawable(activity.getDrawable(R.drawable.appcolorroundcorner))
                holder.tvinterset.setTextColor(activity.getColor(R.color.white))
            }
            isselected = !isselected
            clicklistner.passdata(arrayList)

        }



    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var linmain = itemView.findViewById<LinearLayout>(R.id.linmain)
        var tvinterset = itemView.findViewById<TextView>(R.id.tvinterset)


    }
}