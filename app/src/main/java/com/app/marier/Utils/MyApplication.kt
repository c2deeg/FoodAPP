package com.app.marier.Utils

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

class MyApplication : MultiDexApplication()  {
    override fun onCreate() {
        super.onCreate()
        WebServices().create()
        MultiDex.install(applicationContext)
//        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}