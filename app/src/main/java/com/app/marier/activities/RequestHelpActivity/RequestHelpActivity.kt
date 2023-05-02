package com.app.marier.activities.RequestHelpActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.marier.R
import com.app.marier.databinding.ActivityNotificationBinding
import com.app.marier.databinding.ActivityRequestHelpBinding

class RequestHelpActivity : AppCompatActivity(), View.OnClickListener {
    private var binding:ActivityRequestHelpBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestHelpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        listeners()
    }
    private fun listeners(){
        binding?.imgback?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.imgback->finish()
        }
    }
}