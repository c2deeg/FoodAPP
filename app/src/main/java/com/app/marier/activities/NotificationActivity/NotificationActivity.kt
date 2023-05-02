package com.app.marier.activities.NotificationActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.marier.R
import com.app.marier.databinding.ActivityNotificationBinding
import com.app.marier.databinding.ActivityPlanSettingBinding

class NotificationActivity : AppCompatActivity() {
    private var binding:ActivityNotificationBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        listners()
    }
    private fun listners(){

    }
}