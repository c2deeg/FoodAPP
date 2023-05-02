package com.app.marier.Utils.ChatApplication

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

class MyNetworkReceiver: BroadcastReceiver() {
    private val TAG = "MyNetworkBroadcast"
    companion object{
        var isActive = false

    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        MyNetworkReceiver.isActive = isOnline(p0!!)
        val activity: Activity = ChatApplication.mActivity!!
        Log.d(TAG, activity.localClassName)
        if (!MyNetworkReceiver.isActive) {
        }
    }


    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected
    }
}