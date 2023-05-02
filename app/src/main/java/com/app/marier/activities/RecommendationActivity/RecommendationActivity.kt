package com.app.marier.activities.RecommendationActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.marier.R
import com.app.marier.adapter.RecommendationRecyclerAdapter
import com.app.marier.databinding.ActivityRecommendationBinding

class RecommendationActivity : AppCompatActivity() {
    private var binding:ActivityRecommendationBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initrecyclerview()

    }
    private fun initrecyclerview(){
        val recommendationRecyclerAdapter  =  RecommendationRecyclerAdapter(this)
        binding?.recommendedrecyclerview?.layoutManager =  GridLayoutManager(this, 2)
        binding?.recommendedrecyclerview?.adapter = recommendationRecyclerAdapter
    }
}