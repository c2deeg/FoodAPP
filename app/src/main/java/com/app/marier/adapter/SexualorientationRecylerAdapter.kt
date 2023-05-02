package com.app.marier.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Models.GetSexualOrientation.Data
import com.app.marier.R
import com.app.marier.`interface`.sexualorientationclicklistner

class SexualorientationRecylerAdapter(
    private val activity: FragmentActivity?,
    private val arrayList: List<Data>,
    private val clicklistener: sexualorientationclicklistner
) : RecyclerView.Adapter<SexualorientationRecylerAdapter.ViewHolder>() {
    private var flag:Boolean = true
    var row_index:Int?=null
    private val selectedItems:ArrayList<String> = ArrayList()



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SexualorientationRecylerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sexualorientationitem, parent, false)
        return SexualorientationRecylerAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return arrayList.size


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvname.text = arrayList.get(position).type
        holder.linmain.setOnClickListener{
            if (flag){
                selectedItems.add(arrayList.get(position)._id)
                holder.linmain.setBackgroundColor(Color.parseColor("#762DC4"));
                holder.tvname.setTextColor(Color.parseColor("#ffffff"));

            }else{
                selectedItems.remove(arrayList.get(position)._id)
                holder.linmain.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.tvname.setTextColor(Color.parseColor("#F1F1F1"));

            }
            flag =!flag
            clicklistener.clicklisdata(selectedItems)
        }



    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tvname = itemView.findViewById<TextView>(R.id.tvname)
        var linmain = itemView.findViewById<LinearLayout>(R.id.linmain)


    }

}