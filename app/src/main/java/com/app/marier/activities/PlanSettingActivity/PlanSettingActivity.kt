package com.app.marier.activities.PlanSettingActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.marier.R
import com.app.marier.StaticModel.PlanSubsViewPagerAdapterModel
import com.app.marier.adapter.ViewPagerAdapter
import com.app.marier.databinding.ActivityPlanSettingBinding

class PlanSettingActivity : AppCompatActivity(), View.OnClickListener {
    private var binding:ActivityPlanSettingBinding?=null
    private var viewPagerAdapter:ViewPagerAdapter?=null
    private var arrayList:ArrayList<PlanSubsViewPagerAdapterModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanSettingBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        listeners()
        initviewpager()

    }
    private fun initviewpager(){
        arrayList.add(PlanSubsViewPagerAdapterModel("Unlimited likes","Send as many likes as you want"))
        arrayList.add(PlanSubsViewPagerAdapterModel("Unlimited followers","Send as many followers as you want"))
        arrayList.add(PlanSubsViewPagerAdapterModel("Unlimited likes","Send as many likes as you want"))
        viewPagerAdapter = ViewPagerAdapter(this,arrayList)
        binding!!.viewPager.adapter = viewPagerAdapter
        binding?.indicator?.setViewPager(binding!!.viewPager)
    }
    private fun listeners(){
        binding!!.linear12month.setOnClickListener(this)
        binding!!.linear6month.setOnClickListener(this)
        binding!!.linear1month.setOnClickListener(this)
        binding!!.imgback.setOnClickListener(this)
        binding!!.tvcontinue.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.linear12month->{
                binding!!.linear12month.setBackgroundDrawable(resources.getDrawable(R.drawable.orangeroundcornerstroke))
                binding!!.linear6month.setBackgroundDrawable(null)
                binding!!.linear1month.setBackgroundDrawable(null)
            }
            R.id.linear6month->{
                binding!!.linear6month.setBackgroundDrawable(resources.getDrawable(R.drawable.orangeroundcornerstroke))
                binding!!.linear12month.setBackgroundDrawable(null)
                binding!!.linear1month.setBackgroundDrawable(null)
            }
            R.id.linear1month->{
                binding!!.linear1month.setBackgroundDrawable(resources.getDrawable(R.drawable.orangeroundcornerstroke))
                binding!!.linear12month.setBackgroundDrawable(null)
                binding!!.linear6month.setBackgroundDrawable(null)
            }
            R.id.imgback->finish()
            R.id.tvcontinue->finish()
        }
    }
}