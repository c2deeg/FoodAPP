package com.app.marier.activities.HomeActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.app.marier.R
import com.app.marier.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    var navController: NavController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navController = Navigation.findNavController(this,R.id.fragmentContainerView3)
       HomeActivity.bottomNavigationView  = findViewById(R.id.bottomnav)

        NavigationUI.setupWithNavController(bottomNavigationView!!,navController!!)
    }

    companion object{
        var bottomNavigationView: BottomNavigationView?=null
    }

}