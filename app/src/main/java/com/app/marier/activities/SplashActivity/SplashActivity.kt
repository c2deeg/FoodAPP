package com.app.marier.activities.SplashActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import com.app.marier.R
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.activities.HomeActivity.HomeActivity
import com.app.marier.activities.OnBoardSplashActivity.OnBoardSplashActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            var status: String ?=null
            status = CSPreferences.readString(this, Utils.USERLOGIN)
            if (status.equals("true")) {
//                    Intent intent2 = new Intent(activity, HomeActivity.class);
                val intent2 = Intent(this, HomeActivity::class.java)
                startActivity(intent2)
                finishAffinity()
            } else {
                val intent2 = Intent(this, OnBoardSplashActivity::class.java)
                startActivity(intent2)
            }



//            val intent2 = Intent(this, OnBoardSplashActivity::class.java)
//            startActivity(intent2)

        }, 3000)
    }
}