package com.app.marier.activities.RecentPassesActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.marier.R
import com.app.marier.adapter.RecentPassesRecyclerAdapter
import com.app.marier.databinding.ActivityRecentPassesBinding

class RecentPassesActivity : AppCompatActivity() {
    private var bindindg:ActivityRecentPassesBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindindg = ActivityRecentPassesBinding.inflate(layoutInflater)
        setContentView(bindindg?.root)
        initRecyclerView()
    }
    private fun initRecyclerView(){
        val recentPassesRecyclerAdapter = RecentPassesRecyclerAdapter(this)
        bindindg?.recentpassedRecyclerview?.layoutManager  = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        bindindg?.recentpassedRecyclerview?.adapter = recentPassesRecyclerAdapter
    }
}